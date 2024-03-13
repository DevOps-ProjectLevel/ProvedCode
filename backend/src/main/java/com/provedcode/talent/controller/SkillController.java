package com.provedcode.talent.controller;

import com.provedcode.talent.model.dto.SkillDTO;
import com.provedcode.talent.service.SkillsService;
import com.provedcode.util.annotations.doc.controller.Skill.GetListOfSkillsApiDoc;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@AllArgsConstructor

@RestController
@RequestMapping("/api/v4")
public class SkillController {
    SkillsService skillsService;
    @GetListOfSkillsApiDoc
    @GetMapping("/skills")
    List<SkillDTO> getFilteredSkills(@RequestParam(value = "filter-by", required = false, defaultValue = "") String filterBy) {
        return skillsService.getFilteredSkills(filterBy);
    }
}
