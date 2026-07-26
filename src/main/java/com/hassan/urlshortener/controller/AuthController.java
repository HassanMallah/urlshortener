package com.hassan.urlshortener.controller;


import com.hassan.urlshortener.dto.RegisterRequestDTO;
import com.hassan.urlshortener.serivce.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDTO registerRequest){
        authService.saveUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}