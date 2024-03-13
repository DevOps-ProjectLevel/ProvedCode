package com.provedcode.util.annotations.doc.controller.user;

import com.provedcode.user.model.dto.UserInfoDTO;
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
@Operation(summary = "Talent Registration")
@ApiResponses(value = {
        @ApiResponse(responseCode = "201",
                description = "CREATED",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = UserInfoDTO.class))),
        @ApiResponse(responseCode = "409",
                description = "CONFLICT (user with login already exists)",
                content = @Content),
        @ApiResponse(
                responseCode = "400",
                description = "BAD_REQUEST",
                content = @Content),
})
public @interface PostTalentRegistrationApiDoc {
}
