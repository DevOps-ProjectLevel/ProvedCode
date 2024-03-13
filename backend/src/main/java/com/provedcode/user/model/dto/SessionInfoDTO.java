package com.provedcode.user.model.dto;

import lombok.Builder;

@Builder
public record SessionInfoDTO(
        String status,
        String token
) {
}