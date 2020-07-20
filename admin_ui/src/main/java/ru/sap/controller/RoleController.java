package ru.sap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String newCategory(Model model) {
        model.addAttribute("role", new Role());
        return "addRole";
    }

    @PostMapping("addRole")
    public String addCategory(Role role) {
        roleService.save(role);
        return "redirect:/newRole";
    }

    @GetMapping("role")
    public String getRoles(Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("activePage", "role");
        return "role";
    }

    @GetMapping("editRole")
    public String editRole(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("role", roleService.findRoleById(id));
        return "addRole";
    }

    @DeleteMapping("deleteRole")
    public String deleteRole(@RequestParam(name = "id") Long id) {
        roleService.deleteRoleById(id);
        return "redirect:/role";
    }
}
