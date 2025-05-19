package com.jvictornascimento.urtshortener.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_URL_SHORTENED", indexes = {
        @Index(name = "idx_hash", columnList = "hash", unique = true)
})
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String hash;

    @Column(nullable = false)
    private String originalUrl;


    private LocalDateTime creationDate;

    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    private Users user;

    @OneToMany(mappedBy = "shortUrl", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccessLog> accessLogs;

    public ShortUrl() {
    }

    public ShortUrl(Long id, String hash, String originalUrl, LocalDateTime creationDate, LocalDateTime expirationDate, Users user) {
        this.id = id;
        this.hash = hash;
        this.originalUrl = originalUrl;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<AccessLog> getAccessLogs() {
        return accessLogs;
    }

    public void setAccessLogs(List<AccessLog> accessLogs) {
        this.accessLogs = accessLogs;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShortUrl shortUrl = (ShortUrl) o;
        return Objects.equals(id, shortUrl.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ShortUrl{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                ", user=" + user +
                ", accessLogs=" + accessLogs +
                '}';
    }
}
