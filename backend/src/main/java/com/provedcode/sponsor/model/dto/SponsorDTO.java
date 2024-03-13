package com.provedcode.sponsor.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SponsorDTO(
        Long id,
        @JsonProperty("first_name")
        @Size(min = 4, max = 20)
        String firstName,
        @JsonProperty("last_name")
        @Size(min = 4, max = 20)
        String lastName,
        @Size(min = 4, max = 1000)
        String image
) {
}