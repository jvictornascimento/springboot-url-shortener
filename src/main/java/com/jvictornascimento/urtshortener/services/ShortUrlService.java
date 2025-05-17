package com.jvictornascimento.urtshortener.services;

import com.jvictornascimento.urtshortener.dtos.ResponseDeleteShortUrlDTO;
import com.jvictornascimento.urtshortener.dtos.ResponseGetShortUrlByUserDTO;
import com.jvictornascimento.urtshortener.dtos.ResponseShortUrlDTO;
import com.jvictornascimento.urtshortener.dtos.ShortUrlDTO;
import com.jvictornascimento.urtshortener.models.AccessLog;
import com.jvictornascimento.urtshortener.models.ShortUrl;
import com.jvictornascimento.urtshortener.models.Users;
import com.jvictornascimento.urtshortener.repositories.AccessLogRepository;
import com.jvictornascimento.urtshortener.repositories.ShortUrlRepository;
import com.jvictornascimento.urtshortener.repositories.UsersRepository;
import com.jvictornascimento.urtshortener.services.exceptions.ExpiredLinkException;
import com.jvictornascimento.urtshortener.services.exceptions.HashNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;

import static com.jvictornascimento.urtshortener.services.utilities.HashGenerator.GeneratorShortHash;

public class ShortUrlService {
    private final UsersRepository usersRepository;
    private final ShortUrlRepository shortUrlRepository;
    private final AccessLogRepository accessLogRepository;

    public ShortUrlService(UsersRepository usersRepository, ShortUrlRepository shortUrlRepository, AccessLogRepository accessLogRepository) {
        this.usersRepository = usersRepository;
        this.shortUrlRepository = shortUrlRepository;
        this.accessLogRepository = accessLogRepository;
    }
    public ResponseShortUrlDTO Shoten(ShortUrlDTO originUrlDTO, Users user){
        var shortUrl = new ShortUrl(
                null,
                GeneratorShortHash(originUrlDTO.url()),
                originUrlDTO.url(),
                0L,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(3),
                user
        );
        shortUrlRepository.save(shortUrl);
        return new ResponseShortUrlDTO("http://short.local/" + shortUrl.getHash(), shortUrl.getOriginalUrl());

    }

    public List<ResponseGetShortUrlByUserDTO> getListByUser(Long id){
        var user = usersRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found!"));
        var list = user.getListURL();
        return list.stream().map(
                u -> new ResponseGetShortUrlByUserDTO(
                        "http://short.local/" + u.getHash(),
                        u.getOriginalUrl(),
                        accessLogRepository.countByShortUrl(u))
        ).toList();
    }

    public ResponseDeleteShortUrlDTO deleteShortUrl(String shortCode) {
        String message ;
        try {
            if (shortUrlRepository.deleteByHash(shortCode) > 0) {
                message = "Link deleted successfully.";
            }else{
                message = "Link not found!"; //depois criar excessao propria
            }
            return new ResponseDeleteShortUrlDTO(message);
        }catch (Exception e){
            throw new RuntimeException("Failed to delete short URL: " + e.getMessage());
        }
    }
    public String getOriginalUrl(String shortCode, HttpServletRequest request){
        System.out.println(shortCode);
        var optionalShortUrl = shortUrlRepository.findByHash(shortCode);
        if(optionalShortUrl.isEmpty()){
            throw new HashNotFoundException(shortCode);
        }

        ShortUrl shortUrl = optionalShortUrl.get();
        if (shortUrl.getExpirationDate() != null && shortUrl.getExpirationDate().isBefore(LocalDateTime.now())){
            System.out.println("chegou aqui");
            throw new ExpiredLinkException();
        }

        AccessLog log = new AccessLog();
        log.setShortUrl(shortUrl);
        log.setIpAddress(request.getRemoteAddr());
        log.setUserAgent(request.getHeader("User-agent"));
        log.setAccessDate(LocalDateTime.now());
        accessLogRepository.save(log);

        return shortUrl.getOriginalUrl();
    }
}
