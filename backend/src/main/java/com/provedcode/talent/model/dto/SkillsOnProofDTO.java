package com.provedcode.talent.model.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record SkillsOnProofDTO(
        Set<SkillDTO> skills
) {
}
