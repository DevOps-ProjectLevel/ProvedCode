package com.provedcode.talent.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record ProofSkillsDTO(
        @NotEmpty
        List<Long> skills
) {
}
