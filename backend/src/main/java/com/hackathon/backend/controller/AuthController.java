package com.hackathon.backend.controller;

import com.hackathon.backend.config.JwtUtil;
import com.hackathon.backend.dto.AuthRequest;
import com.hackathon.backend.dto.AuthResponse;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // accept any username/password
        String token = JwtUtil.generateToken(request.getUsername());
        return new AuthResponse(token);
    
    }
}