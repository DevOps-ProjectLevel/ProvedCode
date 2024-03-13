package com.provedcode.talent.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.provedcode.util.annotations.UrlList;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record FullTalentDTO(
        Long id,
        @NotEmpty
        @JsonProperty("first_name")
        @Size(min = 4, max = 20)
        String firstName,
        @NotEmpty
        @JsonProperty("last_name")
        @Size(min = 4, max = 20)
        String lastName,
        @Size(min = 4, max = 1000)
        String image,
        @NotEmpty
        @Size(min = 4, max = 30)
        String specialization,
        @JsonProperty("additional_info")
        @Size(min = 4, max = 500)
        String additionalInfo,
        @Size(min = 4, max = 2000)
        String bio,
        Set<SkillDTO> skills,
        @UrlList
        List<String> links,
        List<String> contacts,
        @UrlList
        @JsonProperty("attached_files")
        List<String> attachedFiles
) {
}