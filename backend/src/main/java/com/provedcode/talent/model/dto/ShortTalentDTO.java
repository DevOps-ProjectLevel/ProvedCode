package com.provedcode.talent.model.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record ShortTalentDTO(
        Long id,
        String image,
        String firstName,
        String lastName,
        String specialization,
        Set<SkillDTO> skills
) {
}
