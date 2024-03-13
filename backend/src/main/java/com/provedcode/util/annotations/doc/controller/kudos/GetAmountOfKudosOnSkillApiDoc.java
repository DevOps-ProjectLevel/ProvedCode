package com.provedcode.util.annotations.doc.controller.kudos;

import com.provedcode.kudos.model.response.KudosAmount;
import com.provedcode.talent.model.dto.SkillsOnProofDTO;
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
@Operation(summary = "Get Amount Of Kudos On Skill")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "SUCCESS",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = KudosAmount.class))),
        @ApiResponse(responseCode = "404",
                description = "NOT FOUND (proof with id = %s not found, Skill with id = %d not found)",
                content = @Content(mediaType = "application/json",
                        schema = @Schema)),
        @ApiResponse(
                responseCode = "401",
                description = "UNAUTHORIZED",
                content = @Content(mediaType = "application/json",
                        schema = @Schema)),
        @ApiResponse(
                responseCode = "403",
                description = "FORBIDDEN (The skill from the proof that was referred to does not have a PUBLISHED status)",
                content = @Content(mediaType = "application/json",
                        schema = @Schema)),
        @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST",
                content = @Content(mediaType = "application/json",
                        schema = @Schema))
})
public @interface GetAmountOfKudosOnSkillApiDoc {
}
