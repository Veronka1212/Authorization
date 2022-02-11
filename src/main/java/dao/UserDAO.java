package dao;

import entity.Role;
import entity.User;
import exeption.DaoException;
import exeption.ValidationException;
import jdbc.BasicConnectionPool;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class UserDAO implements Dao<String, User> {

    private static final UserDAO INSTANCE = new UserDAO();
    private static final String GET_BY_NAME_AND_EMAIL =
            "SELECT * FROM users.myusers WHERE name=? AND email=?";
    private static final String GET_BY_EMAIL_AND_PASS =
            "SELECT * FROM users.myusers WHERE email=? AND password=?";
    private static final String SAVE_USER =
            "INSERT INTO users.myusers (name, email, password, role) VALUES (?,?,?,?)";


    public static UserDAO getInstance() {
        return INSTANCE;
    }

    public Optional<User> findByEmailAndPass(String email, String password) throws SQLException {
        try (Connection connection = BasicConnectionPool.connectPool().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASS);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getObject("id", Integer.class))
                        .name(resultSet.getObject("email", String.class))
                        .email(resultSet.getObject("password", String.class))
                        .role(Role.valueOf(resultSet.getObject("role", String.class)))
                        .build();
            }
            return Optional.ofNullable(user);
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByParameter(String parameter) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean delete(String parameter) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public User save(User entity) throws SQLException {
        try (Connection connection = BasicConnectionPool.connectPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER, Statement.RETURN_GENERATED_KEYS)) {
            if (!findByEmailAndPass(entity.getName(),entity.getEmail()).equals(Optional.empty())){
                throw new DaoException(entity);
            }
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getEmail());
            preparedStatement.setObject(3, entity.getPassword());
            preparedStatement.setObject(4, entity.getRole().name());
            preparedStatement.executeUpdate();
            return entity;
        }
    }
}

