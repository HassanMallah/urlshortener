package com.hassan.urlshortener.controller;

import com.hassan.urlshortener.dto.ShortenRequest;
import com.hassan.urlshortener.dto.UrlResponse;
import com.hassan.urlshortener.entity.Url;
import com.hassan.urlshortener.serivce.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/my-urls")
    public ResponseEntity<List<UrlResponse>> myUrls(@RequestParam UUID userId){

        List<UrlResponse> allUrl = urlService.getMyUrls(userId);
        return ResponseEntity.ok(allUrl);
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortCode, @RequestParam UUID userId){

        urlService.deleteUrl(shortCode, userId);
        return ResponseEntity.noContent().build();
    }
}