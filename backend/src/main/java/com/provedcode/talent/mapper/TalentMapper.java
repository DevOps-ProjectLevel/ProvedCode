package com.provedcode.talent.mapper;

import com.provedcode.talent.model.dto.FullTalentDTO;
import com.provedcode.talent.model.dto.ShortTalentDTO;
import com.provedcode.talent.model.entity.Talent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TalentMapper {
    @Mapping(target = "bio", expression = "java(talent.getTalentDescription() != null ? talent.getTalentDescription().getBio() : null)")
    @Mapping(target = "additionalInfo", expression = "java(talent.getTalentDescription() != null ? talent.getTalentDescription().getAdditionalInfo() : null)")
    @Mapping(target = "links", expression = "java(talent.getTalentLinks().stream().map(l -> l.getLink()).toList())")
    @Mapping(target = "contacts", expression = "java(talent.getTalentContacts().stream().map(c -> c.getContact()).toList())")
    @Mapping(target = "attachedFiles", expression = "java(talent.getTalentAttachedFiles().stream().map(a -> a.getAttachedFile()).toList())")
    FullTalentDTO talentToFullTalentDTO(Talent talent);

    ShortTalentDTO talentToShortTalentDTO(Talent talent);
}