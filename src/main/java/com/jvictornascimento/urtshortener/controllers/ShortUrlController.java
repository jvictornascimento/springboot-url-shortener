package com.jvictornascimento.urtshortener.controllers;

import com.jvictornascimento.urtshortener.dtos.ResponseDeleteShortUrlDTO;
import com.jvictornascimento.urtshortener.dtos.ResponseGetShortUrlByUserDTO;
import com.jvictornascimento.urtshortener.dtos.ResponseShortUrlDTO;
import com.jvictornascimento.urtshortener.dtos.ShortUrlDTO;
import com.jvictornascimento.urtshortener.services.ShortUrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ShortUrlController {

    private final ShortUrlService service;

    public ShortUrlController(ShortUrlService service) {
        this.service = service;
    }

    @PostMapping(value = "/shorten")
    public ResponseEntity<ResponseShortUrlDTO> shortenUrl(@RequestBody ShortUrlDTO originUrl){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var jwt = (Jwt) auth.getPrincipal();
        return ResponseEntity.ok().body(service.Shoten(originUrl, jwt.getSubject()));
    }

    @GetMapping(value = "/links")
    public ResponseEntity<List<ResponseGetShortUrlByUserDTO>> getListByUser(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var jwt = (Jwt) auth.getPrincipal();
        return ResponseEntity.ok().body(service.getListByUser(jwt.getSubject()));
    }

    @DeleteMapping(value = "/links/{short_code}")
    public ResponseEntity<ResponseDeleteShortUrlDTO> deleteShortUrl(@PathVariable String short_code){
        return ResponseEntity.ok().body(service.deleteShortUrl(short_code));
    }
}
