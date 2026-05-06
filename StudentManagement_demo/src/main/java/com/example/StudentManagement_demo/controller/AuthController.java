package com.example.StudentManagement_demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudentManagement_demo.dto.LoginDTO;
import com.example.StudentManagement_demo.dto.RegisterDTO;
import com.example.StudentManagement_demo.service.AuthService;

// Handles authentication APIs like login and register

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    // API for user registration
    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO dto) {
        return service.register(dto);
    }

    // API for user login
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        return service.login(dto.getUsername(), dto.getPassword());
    }
}