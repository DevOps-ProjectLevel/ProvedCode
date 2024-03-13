package com.provedcode.kudos.model.response;

import com.provedcode.sponsor.model.dto.SponsorDTO;
import lombok.Builder;

import java.util.Map;

@Builder
public record KudosAmountOnProofWithSponsor(
        Long allKudosOnProof,
        Map<Long, SponsorDTO> kudosFromSponsor
) {
}
