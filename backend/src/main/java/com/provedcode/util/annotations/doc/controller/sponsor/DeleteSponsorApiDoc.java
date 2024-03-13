package com.provedcode.util.annotations.doc.controller.sponsor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Delete sponsor",
        description = "As a sponsor I want to have an opportunity to delete personal accounts.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "SUCCESS",
                content = @Content),
        @ApiResponse(responseCode = "404",
                description = "NOT FOUND ",
                content = @Content),
        @ApiResponse(
                responseCode = "401",
                description = "UNAUTHORIZED",
                content = @Content),
        @ApiResponse(
                responseCode = "403",
                description = "FORBIDDEN (if not the owner wants to delete the talent)",
                content = @Content),
        @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST (incorrect id)",
                content = @Content),
        @ApiResponse(
                responseCode = "501",
                description = "NOT_IMPLEMENTED (login is not valid)",
                content = @Content),
})
public @interface DeleteSponsorApiDoc {
}
