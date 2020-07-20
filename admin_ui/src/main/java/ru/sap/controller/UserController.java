package ru.sap.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sap.database.model.User;
import ru.sap.dto.UserDTO;
import ru.sap.service.RoleService;
import ru.sap.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class UserController {
    private UserService userService;
    private RoleService roleService;
    private ModelMapper modelMapper;

    public UserController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "addUser";
    }

    @PostMapping("addUser")
    public String addUser(User user) {
        userService.saveUser(user, false);
        return "redirect:/newUser";
    }

    @GetMapping("user")
    public String getUsers(Model model) {

//        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
//            @Override
//            protected void configure() {
//                map().setName(source.getName());
//            }
//        });

        List<UserDTO> users = userService.findAll()
                                         .stream()
                                         .map(user -> {
                                             UserDTO userDTO = new UserDTO();
                                             modelMapper.map(user, userDTO);
                                             return userDTO;
                                         })
                                         .collect(Collectors.toList());
        model.addAttribute("users", users);
        model.addAttribute("activePage", "user");
        return "user";
    }

    @GetMapping("editUser")
    public String editUser(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "addUser";
    }

    @DeleteMapping("deleteUser")
    public String deleteUser(@RequestParam(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }
}
