package exeption;

import entity.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DaoException extends RuntimeException{
    private final User errorUser;

    public String getMessage() {
        return "Such user already exists";
    }
}
