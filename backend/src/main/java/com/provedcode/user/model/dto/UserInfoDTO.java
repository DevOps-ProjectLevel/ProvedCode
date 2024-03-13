package com.provedcode.user.model.dto;

import lombok.Builder;

@Builder
public record UserInfoDTO(
        String token,
        Long id,
        String role
) {
}