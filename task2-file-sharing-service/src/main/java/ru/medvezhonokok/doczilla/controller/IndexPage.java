package ru.medvezhonokok.doczilla.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPage extends Page {
    @GetMapping({"", "/"})
    public String index() {
        return "IndexPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        unsetUser(httpSession);
        setMessage(httpSession, "Good bye!");
        return "redirect:";
    }
}
