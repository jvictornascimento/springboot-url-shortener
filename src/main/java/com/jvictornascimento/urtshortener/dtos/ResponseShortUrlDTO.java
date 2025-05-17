package com.jvictornascimento.urtshortener.dtos;

public record ResponseShortUrlDTO(
        String short_url,
        String original_url
) {
}
