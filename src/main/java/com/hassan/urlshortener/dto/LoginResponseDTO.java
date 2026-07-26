package com.hassan.urlshortener.dto;

public record LoginResponseDTO(
        String token,
        String name,
        String email,
        String role
) {
}