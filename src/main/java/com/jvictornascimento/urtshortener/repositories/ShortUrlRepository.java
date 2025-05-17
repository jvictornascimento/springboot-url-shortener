package com.jvictornascimento.urtshortener.repositories;

import com.jvictornascimento.urtshortener.models.ShortUrl;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    @Transactional
    long deleteByHash(String hash);

    Optional<ShortUrl> findByHash(String shortCode);
    List<ShortUrl> findByExpirationDateBefore(LocalDateTime expirationDate);
}
