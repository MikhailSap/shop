package ru.sap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sap.database.model.User;
import ru.sap.service.RoleService;

@Controller
public class LoginController {
    private RoleService roleService;

    public LoginController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping
    public String index() {
        return "index";
    }
}
