package com.provedcode.util.annotations.doc.controller.talent;

import com.provedcode.talent.model.dto.ShortTalentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Filtered By Skills Talents",
        description = "As a guest I want to filter talents by the skills. (ShortTalentDTO in Page.class)")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "SUCCESS",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ShortTalentDTO.class))),
        @ApiResponse(responseCode = "404",
                description = "NOT FOUND"),
        @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST")
})
public @interface GetFilteredBySkillsTalentsApiDoc {
}
