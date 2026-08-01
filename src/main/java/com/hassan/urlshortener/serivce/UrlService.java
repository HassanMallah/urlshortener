package com.hassan.urlshortener.serivce;

import com.hassan.urlshortener.dto.ShortenRequest;
import com.hassan.urlshortener.dto.UrlResponse;
import com.hassan.urlshortener.entity.Url;
import com.hassan.urlshortener.entity.User;
import com.hassan.urlshortener.repository.UrlRepository;
import com.hassan.urlshortener.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {

    private String baseUrl = "http://localhost:8080";

    private final UrlRepository urlRepository;
    private final UserRepository userRepository;

    public UrlService(UrlRepository urlRepository, UserRepository userRepository) {
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
    }

    public UrlResponse shortenUrl(ShortenRequest shortenRequest) {

        String shortCode;
        do {
            shortCode = UUID.randomUUID().toString().substring(0, 6);
        } while (urlRepository.findByShortCode(shortCode).isPresent());

        User user = userRepository.findByEmail("hassanmallah@gmail.com")
                .orElseThrow(() -> new RuntimeException("Test user not found"));

        Url url = new Url();
        url.setOriginalUrl(shortenRequest.originalUrl());
        url.setShortCode(shortCode);
        url.setCreatedBy(user);
        url.setCreatedAt(Instant.now());
        url.setActive(true);
        url.setTotalClicks(0L);

        urlRepository.save(url);

        return new UrlResponse(
                url.getShortCode(),
                baseUrl + "/" + url.getShortCode(),
                url.getOriginalUrl(),
                url.getTotalClicks(),
                url.getCreatedAt().toString(),
                url.isActive()
        );
    }

    public List<UrlResponse> getMyUrls(UUID userId) {

       return urlRepository.findByCreatedById(userId).stream()
                .map(url -> new UrlResponse(
                        url.getShortCode(),
                        baseUrl + "/" + url.getShortCode(),
                        url.getOriginalUrl(),
                        url.getTotalClicks(),
                        url.getCreatedAt().toString(),
                        url.isActive()
                ))
                .toList();
    }

    public void deleteUrl( String shortCode ,UUID usrId) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Url not Found"));

        if(!url.getCreatedBy().getId().equals(usrId)){
            throw new RuntimeException("You do not have permission to delete this URL");
        }

        urlRepository.delete(url);
    }
}