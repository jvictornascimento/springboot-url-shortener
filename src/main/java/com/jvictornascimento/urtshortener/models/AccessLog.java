package com.jvictornascimento.urtshortener.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_ACCESS_LOG")
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipAddress;

    // FK para User, evita palavra reservada
    private String userAgent;

    private LocalDateTime accessDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "short_url_id")
    private ShortUrl shortUrl;

    public  AccessLog() {

    }

    public AccessLog(Long id, String ipAddress, String userAgent, LocalDateTime accessDate, ShortUrl shortUrl) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.accessDate = accessDate;
        this.shortUrl = shortUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public LocalDateTime getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(LocalDateTime accessDate) {
        this.accessDate = accessDate;
    }

    public ShortUrl getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(ShortUrl shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AccessLog accessLog = (AccessLog) o;
        return Objects.equals(id, accessLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AccessLog{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", accessDate=" + accessDate +
                ", shortUrl=" + shortUrl +
                '}';
    }
}
