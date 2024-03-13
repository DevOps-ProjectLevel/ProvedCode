package com.provedcode.util.annotations.doc.controller.proof;

import com.provedcode.talent.model.dto.ProofDTO;
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
@Operation(summary = "Edit information about proof",
        description = "As a talent I want to have an opportunity to edit my personal proofs")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "SUCCESS",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ProofDTO.class))),
        @ApiResponse(responseCode = "404",
                description = "NOT FOUND",
                content = @Content),
        @ApiResponse(
                responseCode = "401",
                description = "Unauthorized",
                content = @Content),
        @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST, (wrong data to edit or incorrect talent-id, proof-id)",
                content = @Content),
        @ApiResponse(
                responseCode = "403",
                description = "FORBIDDEN (if not the owner wants to edit the proof)",
                content = @Content)
})
public @interface PatchEditProofApiDoc {
}
