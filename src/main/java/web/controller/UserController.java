package web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import web.service.UserService;

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

