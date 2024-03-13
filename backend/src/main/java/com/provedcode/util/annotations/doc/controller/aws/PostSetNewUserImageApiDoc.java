package com.provedcode.util.annotations.doc.controller.aws;

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
@Operation(summary = "Set new Talent`s image")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "SUCCESS"),
        @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST",
                content = @Content),
        @ApiResponse(
                responseCode = "401",
                description = "UNAUTHORIZED",
                content = @Content),
        @ApiResponse(
                responseCode = "403",
                description = "FORBIDDEN",
                content = @Content),
        @ApiResponse(
                responseCode = "404",
                description = "NOT FOUND",
                content = @Content),
        @ApiResponse(
                responseCode = "503",
                description = "SERVICE UNAVAILABLE (connection to AWS S3 failed)",
                content = @Content
        )
})
public @interface PostSetNewUserImageApiDoc {
}
