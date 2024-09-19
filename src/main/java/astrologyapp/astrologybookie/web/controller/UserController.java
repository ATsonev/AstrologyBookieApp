package astrologyapp.astrologybookie.web.controller;

import astrologyapp.astrologybookie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllClients());
        return "users";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
