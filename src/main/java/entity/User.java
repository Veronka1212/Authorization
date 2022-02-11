package entity;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@Setter
@Builder
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private Role role;
}
