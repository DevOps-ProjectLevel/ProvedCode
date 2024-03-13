package com.provedcode.sponsor.mapper;

import com.provedcode.sponsor.model.dto.SponsorDTO;
import com.provedcode.sponsor.model.entity.Sponsor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SponsorMapper {
    SponsorDTO toDto(Sponsor sponsor);

    Sponsor toEntity(SponsorDTO sponsorDTO);
}