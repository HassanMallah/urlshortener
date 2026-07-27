package com.hassan.urlshortener.serivce;

import com.hassan.urlshortener.dto.LoginRequestDTO;
import com.hassan.urlshortener.dto.LoginResponseDTO;
import com.hassan.urlshortener.dto.RegisterRequestDTO;
import com.hassan.urlshortener.entity.User;
import com.hassan.urlshortener.enums.Roles;
import com.hassan.urlshortener.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(RegisterRequestDTO registerRequest) {
        User user = new User();

        user.setName(registerRequest.name());
        user.setEmail(registerRequest.email());
        user.setPassword(registerRequest.password());
        user.setRole(Roles.USER);
        user.setEnabled(true);
        user.setCreatedAt(Instant.now());

        this.userRepository.save(user);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {

        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!loginRequest.password().equals(user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        return new LoginResponseDTO(
                null,
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}