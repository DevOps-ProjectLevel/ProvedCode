package com.provedcode.util.annotations.impl;

import com.provedcode.util.annotations.UrlList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.net.URL;
import java.util.List;

public class UrlListValidator implements ConstraintValidator<UrlList, List<String>> {
    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        for (String url : value) {
            if (!isUrl(url)) {
                return false;
            }
        }
        return true;
    }

    private boolean isUrl(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}