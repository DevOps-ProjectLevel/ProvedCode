package com.provedcode.talent.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.data.domain.Page;


@Builder
public record FullProofDTO (
        Long id,
        @NotEmpty
        @JsonProperty("first_name")
        String firstName,
        @NotEmpty
        @JsonProperty("last_name")
        String lastName,
        String image,
        @NotEmpty
        String specialization,
        Page<ProofDTO> proofs
) {
}
