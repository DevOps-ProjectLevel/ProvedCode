package com.provedcode.kudos.model.response;

import com.provedcode.sponsor.model.dto.SponsorDTO;
import lombok.Builder;

import java.util.Map;

@Builder
public record KudosAmountWithSponsor(
        Long allKudosOnProof,
        Map<String, Map<Long, SponsorDTO>> kudosFromSponsor
) {
}
