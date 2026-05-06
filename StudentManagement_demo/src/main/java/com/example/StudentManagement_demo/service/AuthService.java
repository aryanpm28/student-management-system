package com.example.StudentManagement_demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.StudentManagement_demo.dto.RegisterDTO;
import com.example.StudentManagement_demo.entity.User;
import com.example.StudentManagement_demo.repository.UserRepository;
import com.example.StudentManagement_demo.security.JwtUtil;

@Service
public class AuthService {

    private final UserRepository repo;
    private final JwtUtil jwt;

    public AuthService(UserRepository repo, JwtUtil jwt) {
        this.repo = repo;
        this.jwt = jwt;
    }

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String register(RegisterDTO dto) {

        if (repo.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username exists");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole("USER");

        repo.save(user);

        return "Registered";
    }

    public String login(String username, String password) {

        User user = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwt.generateToken(username);
    }
}
