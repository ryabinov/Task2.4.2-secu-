package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.RoleDAO;
import web.model.Role;
import web.model.User;
import web.service.UserService;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleDAO roleDAO;

    public AdminController(UserService userService, RoleDAO roleDAO) {
        this.userService = userService;
        this.roleDAO = roleDAO;
    }

    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("usersList", userService.allUsers());
        return "usersPage";
    }

    @GetMapping(value = "/add")
    public String addPage() {
        return "addUser";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {

        HashSet<Role> Setroles = new HashSet<>();
        Role role_admin = roleDAO.createRoleIfNotFound("ADMIN", 1L);
        Role role_user = roleDAO.createRoleIfNotFound("USER", 2L);
        Setroles.add(role_admin);
        Setroles.add(role_user);
        model.addAttribute("rolelist", Setroles);
        model.addAttribute("user", userService.getById(id));
        return "editUser";

    }

    @PostMapping(value = "/edit")
    public String editUser(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("lastname") String lastname,
            @RequestParam("age") byte age,
            @RequestParam("roles") String[] roles
    ) {
        User user = userService.getById(id);
        user.setName(name);
        user.setLastname(lastname);
        user.setAge(age);

        if (!password.isEmpty()) {
            user.setPassword(password);
        }

        Set<Role> Setroles = new HashSet<>();

        for (String role : roles) {
            if (role.equals("ADMIN")) {
                Role role_admin = roleDAO.createRoleIfNotFound("ADMIN", 1L);
                Setroles.add(role_admin);
            }
            if (role.equals("USER")) {
                Role role_user = roleDAO.createRoleIfNotFound("USER", 2L);
                Setroles.add(role_user);
            }
        }

        user.setRoles(Setroles);
        userService.save(user);
        return "redirect:/admin";
    }


    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.delete(user);
        return "redirect:/admin";
    }


}
