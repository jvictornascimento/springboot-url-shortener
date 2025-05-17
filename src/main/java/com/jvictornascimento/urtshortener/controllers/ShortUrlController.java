package com.jvictornascimento.urtshortener.controllers;

import com.jvictornascimento.urtshortener.config.security.SecurityConfig;
import com.jvictornascimento.urtshortener.dtos.ResponseDeleteShortUrlDTO;
import com.jvictornascimento.urtshortener.dtos.ResponseGetShortUrlByUserDTO;
import com.jvictornascimento.urtshortener.dtos.ResponseShortUrlDTO;
import com.jvictornascimento.urtshortener.dtos.ShortUrlDTO;
import com.jvictornascimento.urtshortener.services.ShortUrlService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "ShortUrl", description = "Controller where the user use to shorten, list and delete url")
public class ShortUrlController {

    private final ShortUrlService service;

    public ShortUrlController(ShortUrlService service) {
        this.service = service;
    }

    @ApiResponse(responseCode = "200", description = "Shortened successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "500", description = "Server Error")
    @PostMapping(value = "/shorten")
    public ResponseEntity<ResponseShortUrlDTO> shortenUrl(@RequestBody ShortUrlDTO originUrl){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var jwt = (Jwt) auth.getPrincipal();
        return ResponseEntity.ok().body(service.Shoten(originUrl, jwt.getSubject()));
    }

    @ApiResponse(responseCode = "200", description = "Listed successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "500", description = "Server Error")
    @GetMapping(value = "/links")
    public ResponseEntity<List<ResponseGetShortUrlByUserDTO>> getListByUser(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var jwt = (Jwt) auth.getPrincipal();
        return ResponseEntity.ok().body(service.getListByUser(jwt.getSubject()));
    }

    @ApiResponse(responseCode = "200", description = "Deleted successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "500", description = "Server Error")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @DeleteMapping(value = "/links/{short_code}")
    public ResponseEntity<ResponseDeleteShortUrlDTO> deleteShortUrl(@PathVariable String short_code){
        return ResponseEntity.ok().body(service.deleteShortUrl(short_code));
    }
}
