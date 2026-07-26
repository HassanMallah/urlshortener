package com.hassan.urlshortener.serivce;

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
}