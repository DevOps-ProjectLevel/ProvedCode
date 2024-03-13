package com.provedcode.util.annotations.doc.controller.Skill;

import com.provedcode.talent.model.dto.FullTalentDTO;
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
@Operation(summary = "Get All Skills On Proof",
        description = "Users can see the list of proof's skills.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "SUCCESS",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = SkillsOnProofDTO.class))),
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
                description = "BAD REQUEST (talentId with id = %s and proofId with id = %s do not match)",
                content = @Content(mediaType = "application/json",
                        schema = @Schema))
})
public @interface GetAllSkillsOnProofApiDoc {
}
