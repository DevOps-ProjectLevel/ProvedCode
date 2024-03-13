package com.provedcode.util.annotations.doc.controller.proof;

import com.provedcode.talent.model.dto.ProofDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
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
@Operation(summary = "Add proof",
        description = "As a talent I want to have an opportunity to add my personal proof")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "SUCCESS",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ProofDTO.class)),
                headers = {@Header(name = "Location",
                        description = "The URI of the created proof",
                        schema = @Schema(type = "string"))}
        ),
        @ApiResponse(responseCode = "404",
                description = "NOT FOUND",
                content = @Content),
        @ApiResponse(
                responseCode = "401",
                description = "Unauthorized",
                content = @Content),
        @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST, (wrong data to add or incorrect talent-id)",
                content = @Content),
        @ApiResponse(
                responseCode = "403",
                description = "FORBIDDEN (if not the owner wants to add the proof)",
                content = @Content)
})
public @interface PostAddProofApiDoc {
}
