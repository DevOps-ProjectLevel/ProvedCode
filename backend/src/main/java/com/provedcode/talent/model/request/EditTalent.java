package com.provedcode.talent.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.provedcode.util.annotations.UrlList;
import lombok.Builder;

import java.util.List;

@Builder
public record EditTalent(
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        String image,
        String specialization,
        @JsonProperty("additional_info")
        String additionalInfo,
        String bio,
//        List<String> talents,
        @UrlList
        List<String> links,
        List<String> contacts,
        @UrlList
        @JsonProperty("attached_files")
        List<String> attachedFiles
) {
}