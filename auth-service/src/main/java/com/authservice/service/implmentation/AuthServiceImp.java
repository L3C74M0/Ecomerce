package com.authservice.service.implmentation;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import com.authservice.dto.UserRequestDTO;
import com.authservice.entitiy.User;
import com.authservice.exception.EmailAlreadyExistsException;
import com.authservice.exception.InvalidCredentialsException;
import com.authservice.exception.NotFoundException;
import com.authservice.repository.UserRepository;
import com.authservice.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Override
    public User findByEmail(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Credenciales inválidas");
        }

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public void register(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("El email ya está en uso");
        }

        User user = new User();
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setRole(userRequestDTO.getRole());

        userRepository.save(user);
    }

}
