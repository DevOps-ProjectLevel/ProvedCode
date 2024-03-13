package com.provedcode.talent.model.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record StatisticsDTO(
        Long allKudosOnTalent,
        Map<String, Long> skillWithLargestNumberOfKudos,
        Map<ProofDTO, Long> proofWithLargestNumberOfKudos
) {
}
