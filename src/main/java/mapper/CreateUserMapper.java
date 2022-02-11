package mapper;

import dto.CreateUserDTO;
import entity.Role;
import entity.User;

public class CreateUserMapper implements Mapperalable<CreateUserDTO, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    @Override
    public User mapFrom(CreateUserDTO object) {
        return User.builder()
                .name(object.getName())
                .email(object.getEmail())
                .password(object.getPassword())
                .role(Role.valueOf(object.getRole()))
                .build();
    }
    public static CreateUserMapper getInstance(){
        return INSTANCE;
    }
}
