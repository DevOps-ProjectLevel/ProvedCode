package com.provedcode.sponsor.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record EditSponsor(
        Long id,
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        String image,
        @JsonProperty("count_of_kudos")
        Long countOfKudos
) {
}
