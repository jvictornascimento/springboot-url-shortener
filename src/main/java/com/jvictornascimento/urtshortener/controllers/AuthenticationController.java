package com.jvictornascimento.urtshortener.controllers;

import com.jvictornascimento.urtshortener.config.security.SecurityConfig;
import com.jvictornascimento.urtshortener.dtos.ResponseLoginDTO;
import com.jvictornascimento.urtshortener.services.AuthenticationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "Login", description = "Controller where the user use to login")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ApiResponse(responseCode = "200", description = "Login successfully")
    @ApiResponse(responseCode = "401", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server Error")
    @PostMapping(value = "/login")
    public ResponseEntity<ResponseLoginDTO> login(Authentication authentication) {
        return ResponseEntity.ok(authenticationService.authentication(authentication));
    }
}
