package com.hassan.urlshortener.dto;

import java.time.LocalDateTime;

public record UrlResponse(
        String shortCode,
        String shortUrl,
        String originalUrl,
        Long totalClicks,
        LocalDateTime createdAt,
        boolean active
) {
}