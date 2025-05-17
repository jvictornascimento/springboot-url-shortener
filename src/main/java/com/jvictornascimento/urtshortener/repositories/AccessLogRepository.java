package com.jvictornascimento.urtshortener.repositories;

import com.jvictornascimento.urtshortener.models.AccessLog;
import com.jvictornascimento.urtshortener.models.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
    long countByShortUrl(ShortUrl shortUrl);
}
