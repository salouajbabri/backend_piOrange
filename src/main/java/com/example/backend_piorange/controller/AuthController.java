package com.example.backend_piorange.controller;

import com.example.backend_piorange.entity.User;
import com.example.backend_piorange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        User user = userService.login(loginRequest.getPhoneNumber(), loginRequest.getPassword());
        if (user != null) {
            return "Login successful!";
        }
        return "Invalid phone number or password.";
    }
}