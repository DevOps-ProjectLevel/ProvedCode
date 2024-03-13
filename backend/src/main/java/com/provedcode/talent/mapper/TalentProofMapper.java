package com.provedcode.talent.mapper;

import com.provedcode.talent.model.dto.ProofDTO;
import com.provedcode.talent.model.entity.TalentProof;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TalentProofMapper {
    @Mapping(source = "created", target = "created", dateFormat = "dd-MM-yyyy HH:mm:ss")
    ProofDTO toProofDTO(TalentProof talentProof);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "created", target = "created", dateFormat = "dd-MM-yyyy HH:mm:ss")
    TalentProof toTalentProof(ProofDTO proofDTO);
}