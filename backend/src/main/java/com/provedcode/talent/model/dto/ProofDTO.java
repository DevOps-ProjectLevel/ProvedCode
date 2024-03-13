package com.provedcode.talent.model.dto;

import com.provedcode.talent.model.ProofStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import org.hibernate.validator.constraints.URL;

@Builder
public record ProofDTO(
        long id,
        @URL
        String link,
        @Size(min = 4, max = 1000)
        String text,
        @NotNull
        ProofStatus status,
        String created
) {
}