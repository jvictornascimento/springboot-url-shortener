package com.jvictornascimento.urtshortener.services;

import com.jvictornascimento.urtshortener.config.security.JwtService;
import com.jvictornascimento.urtshortener.dtos.ResponseLoginDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final JwtService jwtService;

    public AuthenticationService(JwtService tokenService) {
        this.jwtService = tokenService;
    }

    public ResponseLoginDTO authentication(Authentication authentication) {
        return new ResponseLoginDTO(jwtService.generateToken(authentication));
    }
}
