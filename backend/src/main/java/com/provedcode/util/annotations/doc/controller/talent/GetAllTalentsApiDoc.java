package com.provedcode.util.annotations.doc.controller.talent;

import com.provedcode.talent.model.dto.ShortTalentDTO;
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
@Operation(summary = "Get all talents (ShortTalentDTO)",
        description = "As a guest I want to see a page with a list of all “talents” cards displayed with a short description about them")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "SUCCESS",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = Page.class, subTypes = {ShortTalentDTO.class}))),
        @ApiResponse(responseCode = "404",
                description = "NOT FOUND",
                content = @Content),
        @ApiResponse(
                responseCode = "400",
                description = "BAD_REQUEST (parameter: page or size are incorrect)",
                content = @Content)
})
public @interface GetAllTalentsApiDoc {
}
