package ru.medvezhonokok.doczilla.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.medvezhonokok.doczilla.form.UserCredentials;
import ru.medvezhonokok.doczilla.form.validator.UserCredentialsRegisterValidator;
import ru.medvezhonokok.doczilla.service.UserService;

import javax.validation.Valid;

@Controller
public class RegisterPage extends Page {
    private final UserService userService;
    private final UserCredentialsRegisterValidator userCredentialsRegisterValidator;

    public RegisterPage(UserService userService, UserCredentialsRegisterValidator userCredentialsRegisterValidator) {
        this.userService = userService;
        this.userCredentialsRegisterValidator = userCredentialsRegisterValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCredentialsRegisterValidator);
    }

    @GetMapping("/register")
    public String registerGet(Model model) {
        model.addAttribute("registerForm", new UserCredentials());
        return "RegisterPage";
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("registerForm") UserCredentials registerForm,
                               BindingResult bindingResult,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "RegisterPage";
        }

        setUser(httpSession, userService.register(registerForm));
        setMessage(httpSession, "Congrats, you have been registered!");

        return "redirect:/";
    }
}
