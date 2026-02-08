package com.narin.api.user.controller;

import com.narin.api.user.dto.LoginRequest;
import com.narin.api.user.entity.User;
import com.narin.api.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @PostMapping("/register")
    public void register(@RequestBody User user) {
        authService.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        return authService.login(req.getUsername(), req.getPassword());
    }
}
