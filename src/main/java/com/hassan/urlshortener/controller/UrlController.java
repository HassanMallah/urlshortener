package com.hassan.urlshortener.controller;

import com.hassan.urlshortener.dto.ShortenRequest;
import com.hassan.urlshortener.dto.UrlResponse;
import com.hassan.urlshortener.entity.User;
import com.hassan.urlshortener.serivce.UrlService;
import jakarta.validation.Valid;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> urlShortenRequest(@Valid  @RequestBody ShortenRequest shortenRequest){

        UrlResponse urlResponse = urlService.shortenUrl(shortenRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(urlResponse);
    }
}
