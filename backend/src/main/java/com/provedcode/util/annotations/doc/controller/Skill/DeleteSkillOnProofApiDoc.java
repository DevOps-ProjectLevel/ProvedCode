package com.provedcode.util.annotations.doc.controller.Skill;

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
@Operation(summary = "Delete Skill On Proof",
        description = "Talent can delete skills in draft status proofs.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "SUCCESS"),
        @ApiResponse(responseCode = "404",
                description = "NOT FOUND (proof with id = %s not found, talent with id = %s not found)",
                content = @Content(mediaType = "application/json",
                        schema = @Schema)),
        @ApiResponse(
                responseCode = "401",
                description = "UNAUTHORIZED",
                content = @Content(mediaType = "application/json",
                        schema = @Schema)),
        @ApiResponse(
                responseCode = "403",
                description = "FORBIDDEN (you can't see proofs in DRAFT and HIDDEN status)",
                content = @Content(mediaType = "application/json",
                        schema = @Schema)),
        @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST",
                content = @Content(mediaType = "application/json",
                        schema = @Schema)),
        @ApiResponse(
                responseCode = "409",
                description = "CONFLICT (you can`t change another talent, proof status must be DRAFT)",
                content = @Content(mediaType = "application/json",
                        schema = @Schema))
})
public @interface DeleteSkillOnProofApiDoc {
}
