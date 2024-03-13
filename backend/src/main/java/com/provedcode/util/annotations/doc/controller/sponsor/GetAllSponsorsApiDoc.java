package com.provedcode.util.annotations.doc.controller.sponsor;

import com.provedcode.sponsor.model.dto.SponsorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Get all sponsors (SponsorDTO)")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "SUCCESS",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = Page.class, subTypes = {SponsorDTO.class}))),
        @ApiResponse(
                responseCode = "400",
                description = "BAD_REQUEST (parameter: page or size are incorrect)",
                content = @Content)
})
public @interface GetAllSponsorsApiDoc {
}
