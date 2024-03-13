package com.provedcode.talent.service;

import com.provedcode.talent.mapper.SkillMapper;
import com.provedcode.talent.model.dto.SkillDTO;
import com.provedcode.talent.repo.SkillsRepository;
import com.provedcode.talent.repo.TalentRepository;
import com.provedcode.user.repo.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillsService {
    SkillsRepository skillsRepository;
    TalentRepository talentRepository;
    UserInfoRepository userInfoRepository;
    SkillMapper skillMapper;

    public List<SkillDTO> getFilteredSkills(String filterBy) {
        return skillsRepository.findBySkillContainsIgnoreCase(filterBy).stream()
                .map(skillMapper::skillToSkillDTO).toList();
    }
}
