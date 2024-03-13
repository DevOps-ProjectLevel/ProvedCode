package com.provedcode.talent.mapper;

import com.provedcode.talent.model.dto.SkillDTO;
import com.provedcode.talent.model.entity.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkillMapper {
    SkillDTO skillToSkillDTO(Skill skills);
}
