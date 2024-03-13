package com.provedcode.talent.controller;

import com.provedcode.talent.mapper.TalentMapper;
import com.provedcode.talent.model.dto.*;
import com.provedcode.talent.model.request.EditTalent;
import com.provedcode.talent.service.TalentService;
import com.provedcode.user.model.dto.SessionInfoDTO;
import com.provedcode.util.annotations.doc.controller.Skill.GetListOfSkillsApiDoc;
import com.provedcode.util.annotations.doc.controller.talent.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/")
@Tag(name = "talent", description = "Talent API")
@Validated
public class TalentController {
    TalentService talentService;
    TalentMapper talentMapper;

    @GetAllTalentsApiDoc
    @GetMapping("v2/talents")
    @Validated
    Page<ShortTalentDTO> getTalents(@RequestParam(value = "page", defaultValue = "0") @PositiveOrZero Integer page,
                                    @RequestParam(value = "size", defaultValue = "5") @Min(1) @Max(1000) Integer size) {
        return talentService.getTalentsPage(page, size).map(talentMapper::talentToShortTalentDTO);
    }

    @GetTalentApiDoc
    @PreAuthorize("hasAnyRole('TALENT', 'SPONSOR')")
    @GetMapping("v2/talents/{id}")
    FullTalentDTO getTalent(@PathVariable("id") long id, Authentication authentication) {
        log.info("get-talent auth = {}", authentication);
        log.info("get-talent auth.name = {}", authentication.getAuthorities());
        return talentMapper.talentToFullTalentDTO(talentService.getTalentById(id));
    }

    @PatchEditTalentApiDoc
    @PreAuthorize("hasRole('TALENT')")
    @PatchMapping("v2/talents/{talent-id}")
    FullTalentDTO editTalent(@PathVariable("talent-id") long id,
                             @RequestBody @Valid EditTalent editTalent,
                             Authentication authentication) {
        return talentMapper.talentToFullTalentDTO(talentService.editTalent(id, editTalent, authentication));
    }

    @DeleteTalentApiDoc
    @PreAuthorize("hasRole('TALENT')")
    @DeleteMapping("v2/talents/{id}")
    SessionInfoDTO deleteTalent(@PathVariable("id") long id, Authentication authentication) {
        return talentService.deleteTalentById(id, authentication);
    }

    @PostAddSkillOnTalentApiDoc
    @PreAuthorize("hasRole('TALENT')")
    @PostMapping("v4/talents/{talent-id}/skills")
    void addSkillOnTalent(@PathVariable("talent-id") long id,
                          @RequestBody @Valid SkillIdDTO skillIdDTO,
                          Authentication authentication) {
        talentService.addSkillOnTalent(id, skillIdDTO, authentication);
    }

    @DeleteSkillFromTalentApiDoc
    @PreAuthorize("hasRole('TALENT')")
    @DeleteMapping("v4/talents/{talent-id}/skills/{skill-id}")
    void deleteSkillFromTalent(@PathVariable("talent-id") long talentId,
                               @PathVariable("skill-id") long skillId,
                               Authentication authentication) {
        talentService.deleteSkillFromTalent(talentId, skillId, authentication);
    }

    @GetFilteredBySkillsTalentsApiDoc
    @GetMapping("v4/talents")
    Page<ShortTalentDTO> getFilteredBySkillsTalents(@RequestParam(value = "page", defaultValue = "0") @PositiveOrZero Integer page,
                                                    @RequestParam(value = "size", defaultValue = "5") @Min(1) @Max(1000) Integer size,
                                                    @RequestParam(value = "filter-by", required = false) String... filterBy) {
        return talentService.getFilteredBySkillsTalentsPage(page, size, filterBy).map(talentMapper::talentToShortTalentDTO);
    }

    @GetStatisticsForTalentApiDoc
    @PreAuthorize("hasRole('TALENT')")
    @GetMapping("v5/talents/{talent-id}/statistics")
    StatisticsDTO getStatisticsForTalent(@PathVariable("talent-id") long talentId,
                                         Authentication authentication) {
        return talentService.getStatisticsForTalent(talentId, authentication);
    }

    @DeleteDeactivateTalentByIdApiDoc
    @PreAuthorize("hasRole('TALENT')")
    @DeleteMapping("v5/talents/{talent-id}")
    void deactivateTalentById(@PathVariable("talent-id") long talentId,
                              Authentication authentication) {
        talentService.deactivateTalentById(talentId, authentication);
    }
    @GetListOfSkillsOnProofApiDoc
    @PreAuthorize("hasRole('TALENT')")
    @GetMapping("v4/talents/{talent-id}/proofs/skills")
    List<SkillDTO> getAllSkillsOnTalentsProofs(@PathVariable("talent-id") long talentId,
                                               Authentication authentication) {
       return talentService.getAllSkillsOnTalentsProofs(talentId, authentication);
    }
}