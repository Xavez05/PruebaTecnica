package com.application.security.controller;

import com.application.security.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String token = authService.login(credentials.get("username"), credentials.get("password"));
        return Map.of("token", token);
    }

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> credentials) {
        authService.register(credentials.get("username"), credentials.get("password"), "USER");
        return "User registered successfully!";
    }
}
