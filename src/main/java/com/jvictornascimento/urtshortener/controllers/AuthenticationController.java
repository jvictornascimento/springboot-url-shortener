package com.jvictornascimento.urtshortener.controllers;

import com.jvictornascimento.urtshortener.dtos.ResponseLoginDTO;
import com.jvictornascimento.urtshortener.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()

public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseLoginDTO> login(Authentication authentication) {
        return ResponseEntity.ok(authenticationService.authentication(authentication));
    }
}
