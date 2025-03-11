package com.lostfind.lostfind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        // Check if the user is authenticated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            model.addAttribute("username", authentication.getName()); // Add username to the model
        }
        return "index"; // Refers to index.html
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login"; // Refers to login.html
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // Refers to register.html
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.isUsernameTaken(username)) {
            model.addAttribute("error", "Username already registered. Please use a different account or login.");
            return "register"; // Return to the registration page with an error message
        }
        userService.registerUser(username, password);
        model.addAttribute("success", "Registration successful! Please log in.");
        return "login"; // Redirect to the login page with a success message
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        // Get the authenticated user's details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            model.addAttribute("username", authentication.getName()); // Add username to the model
        }
        return "profile"; // Refers to profile.html
    }
}