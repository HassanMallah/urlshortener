package com.hassan.urlshortener.repository;

import com.hassan.urlshortener.entity.Url;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlRepository extends JpaRepository<Url, UUID> {

    Optional<Url> findByShortCode(String shortcode);
    List<Url> findByCreatedById(UUID userId);

    @Modifying
    @Transactional
    @Query("UPDATE Url u SET u.totalClicks = u.totalClicks + 1 WHERE u.id = :id")
    void incrementTotalClicks(@Param("id") UUID id);
}