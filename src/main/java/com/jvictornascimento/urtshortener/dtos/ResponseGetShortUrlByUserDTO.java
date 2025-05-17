package com.jvictornascimento.urtshortener.dtos;

public record ResponseGetShortUrlByUserDTO(
        String short_url,
        String original_url,
        Long clicks
) {
}
