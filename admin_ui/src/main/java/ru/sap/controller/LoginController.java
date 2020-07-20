package ru.sap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sap.service.RoleService;

@Controller
public class LoginController {

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping
    public String index() {
        return "index";
    }
}
