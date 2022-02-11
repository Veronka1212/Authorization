package service;

import dao.UserDAO;
import dto.CreateUserDTO;
import dto.UserDTO;
import entity.User;
import exeption.DaoException;
import exeption.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import mapper.CreateUserMapper;
import mapper.UserMapper;
import validator.CreateUserValidation;
import validator.Validator;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();
    public final UserDAO userDAO = UserDAO.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final CreateUserValidation createUserValidation = CreateUserValidation.getInstance();

    public static UserService getInstance() {
        return INSTANCE;
    }

    public Integer create(CreateUserDTO createUserDTO) throws DaoException {
        Validator validator = createUserValidation.resultOfValidation(createUserDTO);
        if (!validator.resultOfValidation()) {
            throw new ValidationException(validator.getErrors());
        }
        User user = createUserMapper.mapFrom(createUserDTO);
        try {
            userDAO.save(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user.getId();
    }

    @SneakyThrows
    public Optional<UserDTO> login(String email, String password) {
        return userDAO.findByEmailAndPass(email, password)
                .map(userMapper::getFrom);
    }
}
