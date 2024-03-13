package com.provedcode.talent.controller;

import com.provedcode.talent.model.dto.ProofSkillsDTO;
import com.provedcode.talent.model.dto.SkillsOnProofDTO;
import com.provedcode.talent.service.ProofSkillsService;
import com.provedcode.util.annotations.doc.controller.Skill.DeleteSkillOnProofApiDoc;
import com.provedcode.util.annotations.doc.controller.Skill.GetAllSkillsOnProofApiDoc;
import com.provedcode.util.annotations.doc.controller.Skill.PostAddSkillOnProofApiDoc;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@AllArgsConstructor

@RestController
@RequestMapping("/api/v5/")
public class TalentSkillsController {
    ProofSkillsService proofSkillsService;

    @PostAddSkillOnProofApiDoc
    @PostMapping("talents/{talent-id}/proofs/{proof-id}/skills")
    void addSkillOnProof(@PathVariable("talent-id") long talentId,
                         @PathVariable("proof-id") long proofId,
                         @RequestBody @Valid ProofSkillsDTO skills,
                         Authentication authentication) {
        proofSkillsService.addSkillsOnProof(talentId, proofId, skills, authentication);
    }

    @GetAllSkillsOnProofApiDoc
    @GetMapping("proofs/{proof-id}/skills")
    SkillsOnProofDTO getAllSkillsOnProof(@PathVariable("proof-id") long proofId,
                                         Authentication authentication) {
        return proofSkillsService.getAllSkillsOnProof(proofId, authentication);
    }

    @DeleteSkillOnProofApiDoc
    @PreAuthorize("hasRole('TALENT')")
    @DeleteMapping("talents/{talent-id}/proofs/{proof-id}/skills/{skill-id}")
    void deleteSkillOnProof(@PathVariable("talent-id") long talentId,
                            @PathVariable("proof-id") long proofId,
                            @PathVariable("skill-id") long skillId,
                            Authentication authentication) {
        proofSkillsService.deleteSkillOnProof(talentId, proofId, skillId, authentication);
    }
}
