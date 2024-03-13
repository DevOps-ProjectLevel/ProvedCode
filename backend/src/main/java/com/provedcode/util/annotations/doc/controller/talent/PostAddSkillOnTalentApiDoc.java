package com.provedcode.util.annotations.doc.controller.talent;

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
@Operation(summary = "Add skills on talent",
        description = "As a talent I want to manipulate the list of skills in own profile that can confirm this proof")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "SUCCESS"),
        @ApiResponse(responseCode = "404",
                description = "NOT FOUND (Skill with id = %d not found)",
                content = @Content),
        @ApiResponse(
                responseCode = "401",
                description = "UNAUTHORIZED",
                content = @Content),
        @ApiResponse(
                responseCode = "403",
                description = "FORBIDDEN (if not the owner wants to change the talent, " +
                        "Skill with id = %d not found in talent's proofs)",
                content = @Content),
        @ApiResponse(
                responseCode = "409",
                description = "CONFLICT (Skill with id = %d found in talent's skills)",
                content = @Content)
})
public @interface PostAddSkillOnTalentApiDoc {
}
