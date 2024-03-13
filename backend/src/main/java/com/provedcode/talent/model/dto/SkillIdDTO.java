package com.provedcode.talent.model.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record SkillIdDTO(
        @NotEmpty
        List<Long> id
) {
}
