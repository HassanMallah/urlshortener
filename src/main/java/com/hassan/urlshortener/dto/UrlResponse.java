package com.hassan.urlshortener.dto;

public record UrlResponse(
        String shortCode,
        String shortUrl,
        String originalUrl,
        Long totalClicks,
        String createdAt,
        boolean active
) {
}