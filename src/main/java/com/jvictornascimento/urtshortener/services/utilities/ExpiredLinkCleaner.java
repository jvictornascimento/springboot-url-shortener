package com.jvictornascimento.urtshortener.services.utilities;

import com.jvictornascimento.urtshortener.repositories.ShortUrlRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExpiredLinkCleaner {
    private final ShortUrlRepository repository;

    public ExpiredLinkCleaner(ShortUrlRepository repository){
        this.repository = repository;
    }
    @Scheduled(cron = "0 * * * * *")
    public void removeExpiredUrls(){
        LocalDateTime now = LocalDateTime.now();
        var expiredLink = repository.findByExpirationDateBefore(now);
        repository.deleteAll(expiredLink);
    }
}
