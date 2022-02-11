package validator;

import dto.CreateUserDTO;
import entity.Role;
import jdbc.BasicConnectionPool;
import jdk.nashorn.internal.runtime.regexp.RegExp;
import jdk.nashorn.internal.runtime.regexp.RegExpMatcher;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.var;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidation implements Validailiable<CreateUserDTO> {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private final static CreateUserValidation INSTANCE = new CreateUserValidation();

    public static CreateUserValidation getInstance() {
        return INSTANCE;
    }

    @Override
    public Validator resultOfValidation(CreateUserDTO object) {
        Validator validator = new Validator();

        if (!validate(object.getEmail())) {
            validator.addError(Error.of("error.email", "Incorrect email"));
        }
        if (object.getPassword().length()<8){
            validator.addError(Error.of("error.password", "You password must be longer"));
        }
        return validator;
    }

    public static boolean validate(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}