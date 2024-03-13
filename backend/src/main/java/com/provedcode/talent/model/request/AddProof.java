package com.provedcode.talent.model.request;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

public record AddProof(
        @URL
        @NotEmpty
        String link,
        @NotEmpty
        String text
) {
}