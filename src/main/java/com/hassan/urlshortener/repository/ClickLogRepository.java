package com.hassan.urlshortener.repository;

import com.hassan.urlshortener.entity.ClickLog;
import com.hassan.urlshortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClickLogRepository extends JpaRepository<ClickLog, UUID> {

    List<ClickLog> findByUrlId(Url urlId);
}