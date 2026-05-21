package com.example.demo.Controllers;

import com.example.demo.Models.User;
import com.example.demo.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "fragments/Login";
    }

    @GetMapping("/opret-bruger")
    public String createUserPage() {
        return "opret-bruger";
    }

    @PostMapping("/opret-bruger")
    public String createUser(@RequestParam String username,
                             @RequestParam String password,
                             HttpSession session) {

        userService.createUser(username, password);

        User user = userService.login(username, password);
        session.setAttribute("user", user);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

        User user = userService.login(username, password);

        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        }

        return "fragments/Login";
    }
}
