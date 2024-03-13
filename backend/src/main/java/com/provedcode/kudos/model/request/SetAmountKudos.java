package com.provedcode.kudos.model.request;

import jakarta.validation.constraints.PositiveOrZero;

public record SetAmountKudos(
        @PositiveOrZero
        Long amount
) {
}