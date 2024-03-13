package com.provedcode.kudos.controller;

import com.provedcode.kudos.model.response.KudosAmountOnProofWithSponsor;
import com.provedcode.util.annotations.doc.controller.kudos.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.provedcode.kudos.model.request.SetAmountKudos;
import com.provedcode.kudos.model.response.KudosAmount;
import com.provedcode.kudos.model.response.KudosAmountWithSponsor;
import com.provedcode.kudos.service.KudosService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/api/")
public class KudosController {
    KudosService kudosService;

    @GetKudosForSponsorApiDoc
    @PreAuthorize("hasRole('SPONSOR')")
    @GetMapping("v5/sponsors/{sponsor-id}/kudos")
    KudosAmount getKudosForSponsor(@PathVariable("sponsor-id") long sponsorId, Authentication authentication) {
        return kudosService.getKudosForSponsor(sponsorId, authentication);
    }

    @GetAmountOfKudosApiDoc
    @PreAuthorize("hasAnyRole('TALENT', 'SPONSOR')")
    @GetMapping("v5/proofs/{proof-id}/kudos")
    KudosAmountWithSponsor getProofAndSkillsKudos(@PathVariable("proof-id") long proofId, Authentication authentication) {
        return kudosService.getProofAndSkillsKudos(proofId, authentication);
    }

    @GetAmountOfKudosOnProofApiDoc
    @PreAuthorize("hasAnyRole('TALENT', 'SPONSOR')")
    @GetMapping("v3/proofs/{proof-id}/kudos")
    KudosAmountOnProofWithSponsor getProofKudos(@PathVariable("proof-id") long proofId, Authentication authentication) {
        return kudosService.getProofKudos(proofId, authentication);
    }

    @PostAddKudosToProofApiDoc
    @PreAuthorize("hasRole('SPONSOR')")
    @PostMapping("v5/proofs/{proof-id}/kudos")
    void addKudosToProof(@PathVariable("proof-id") long proofId,
            @RequestBody @Valid SetAmountKudos amount,
            Authentication authentication) {
        kudosService.addKudosToProof(proofId, amount, authentication);
    }
    @PostAddKudosToSkillApiDoc
    @PreAuthorize("hasRole('SPONSOR')")
    @PostMapping("v5/proofs/{proof-id}/skills/{skill-id}/kudos")
    void addKudosToSkill(@PathVariable("proof-id") long proofId, @PathVariable("skill-id") long skillId,
            Authentication authentication, @RequestBody @Valid SetAmountKudos amount) {
        kudosService.addKudosToSkill(proofId, skillId, amount, authentication);
    }
    @GetAmountOfKudosOnSkillApiDoc
    @GetMapping("v5/proofs/{proof-id}/skills/{skill-id}/kudos")
    KudosAmount getKudosForSkill(@PathVariable("proof-id") long proofId, @PathVariable("skill-id") long skillId) {
        return kudosService.getSkillKudos(proofId, skillId);
    }
}