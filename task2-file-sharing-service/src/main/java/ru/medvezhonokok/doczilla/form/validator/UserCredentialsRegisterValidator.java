package ru.medvezhonokok.doczilla.form.validator;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.medvezhonokok.doczilla.form.UserCredentials;
import ru.medvezhonokok.doczilla.service.UserService;

@Component
public class UserCredentialsRegisterValidator implements Validator {
    private final UserService userService;

    public UserCredentialsRegisterValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return UserCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserCredentials registerForm = (UserCredentials) target;

            if (Strings.isEmpty(registerForm.getLogin())) {
                errors.rejectValue("login", "login.is-required", "Login is required.");
            } else if (registerForm.getLogin().length() > 16) {
                errors.rejectValue("login", "login.is-long", "Login is must be shorter than 16 characters.");
            }

            if (Strings.isEmpty(registerForm.getPassword())) {
                errors.rejectValue("password", "password.is-required", "Password is required.");
            }

            if (!userService.isLoginFree(registerForm.getLogin())) {
                errors.rejectValue("login", "login.is-in-use", "Login is in use already");
            }


        }
    }
}
