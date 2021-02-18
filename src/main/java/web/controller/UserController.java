package web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import org.springframework.stereotype.Controller;
import web.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ModelAndView showUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("infoUser");
        modelAndView.addObject("user", user);
        return modelAndView;

    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


}

