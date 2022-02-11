package dto;

import entity.Role;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class UserDTO {
    int id;
    String name;
    String email;
    Role role;
}
