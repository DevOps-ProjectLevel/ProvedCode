package com.provedcode.util.annotations;

import com.provedcode.util.annotations.impl.UrlListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UrlListValidator.class)
public @interface UrlList {
    String message() default "The list should contain only URLs";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}