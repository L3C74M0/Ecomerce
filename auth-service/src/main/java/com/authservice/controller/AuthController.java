package com.authservice.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.dto.LoginRequestDTO;
import com.authservice.dto.UserRequestDTO;
import com.authservice.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequestDTO request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
