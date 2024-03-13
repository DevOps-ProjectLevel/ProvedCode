package com.provedcode.talent.model.dto;

import lombok.Builder;

@Builder
public record SkillDTO(
        Long id,
        String skill
) {
}
