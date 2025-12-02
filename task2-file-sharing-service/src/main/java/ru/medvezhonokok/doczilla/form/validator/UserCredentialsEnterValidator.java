package ru.medvezhonokok.doczilla.form.validator;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.medvezhonokok.doczilla.form.UserCredentials;
import ru.medvezhonokok.doczilla.service.UserService;

@Component
public class UserCredentialsEnterValidator implements Validator {
    private final UserService userService;

    public UserCredentialsEnterValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return UserCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserCredentials enterForm = (UserCredentials) target;

            if (Strings.isEmpty(enterForm.getLogin())) {
                errors.rejectValue("login", "login.is-required", "Login is required.");
            }

            if (Strings.isEmpty(enterForm.getPassword())) {
                errors.rejectValue("password", "password.is-required", "Password is required.");
            }

            if (userService.findByLoginAndPassword(enterForm.getLogin(), enterForm.getPassword()) == null) {
                errors.rejectValue(
                        "password", "password.invalid-login-or-password", "Invalid login or password");
            }
        }
    }
}
