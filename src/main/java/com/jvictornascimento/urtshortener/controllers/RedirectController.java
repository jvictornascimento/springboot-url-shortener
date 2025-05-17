package com.jvictornascimento.urtshortener.controllers;

import com.jvictornascimento.urtshortener.config.security.SecurityConfig;
import com.jvictornascimento.urtshortener.services.ShortUrlService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "Redirect", description = "Controller where the user use uses to redirect to the original url")
public class RedirectController {

    private final ShortUrlService service;

    public RedirectController(ShortUrlService service) {
        this.service = service;
    }

    @ApiResponse(responseCode = "302", description = "Redirect successfully")
    @ApiResponse(responseCode = "401", description = "Short code not found")
    @ApiResponse(responseCode = "500", description = "Server Error")
    @GetMapping(value = "/{short_code}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String short_code, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, service.getOriginalUrl(short_code, request))
                .build();
    }
}
