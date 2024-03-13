package com.provedcode.handlers.dto;

import lombok.Builder;

@Builder
public record ErrorDTO (String message) {
}
