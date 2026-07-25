package com.hassan.urlshortener.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "click_logs")
@Getter
@Setter
public class ClickLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_id", nullable = false)
    private Url urlId;

    @Column(name = "ip_address", length = 50)
    private String ipAddress;

    @Column(length = 100)
    private String country;

    @Column(length = 50)
    private String device;

    @Column(length = 50)
    private String browser;

    @Column(length = 50)
    private String os;

    private Instant clickedAt;

    @PrePersist
    void onClicked() {
        if (clickedAt == null) {
            clickedAt = Instant.now();
        }
    }
}