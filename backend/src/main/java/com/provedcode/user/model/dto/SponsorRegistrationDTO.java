package com.provedcode.user.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record SponsorRegistrationDTO(
        @NotEmpty
        @Email
        String login,
        @NotEmpty
        String password,
        @JsonProperty("first_name")
        @NotEmpty
        String firstName,
        @JsonProperty("last_name")
        @NotEmpty
        String lastName
) {
}