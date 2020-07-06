package ru.sap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sap.database.model.ProductCategory;
import ru.sap.database.model.Role;
import ru.sap.service.RoleService;

@Controller
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("newRole")
    private String newCategory(Model model) {
        model.addAttribute("role", new Role());
        return "addRole";
    }

    @PostMapping("addRole")
    private String addCategory(Role role) {
        roleService.save(role);
        return "redirect:/newRole";
    }
}
