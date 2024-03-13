package com.provedcode.util.annotations.doc.controller.Skill;

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
@Operation(summary = "Add Skills On Proof",
        description = "As a talent I want to manipulate list of skills in description of proof that can confirm it")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "SUCCESS"),
        @ApiResponse(responseCode = "404",
                description = "NOT FOUND (user with id = %s not found, " +
                        "talent with id = %s not found, " +
                        "proof with id = %s not found," +
                        "no such skill with id"),
        @ApiResponse(
                responseCode = "401",
                description = "UNAUTHORIZED"),
        @ApiResponse(
                responseCode = "409",
                description = "CONFLICT (you can`t change another talent, " +
                        "skill with id = %s already on skill)"),
})
public @interface PostAddSkillOnProofApiDoc {
}
