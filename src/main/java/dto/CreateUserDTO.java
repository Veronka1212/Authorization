package dto;

import entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDTO {
    String id;
    String name;
    String email;
    String password;
    String role;
}
