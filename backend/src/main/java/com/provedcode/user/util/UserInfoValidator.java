package com.provedcode.user.util;

import com.provedcode.user.model.entity.UserInfo;
import com.provedcode.user.repo.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
@AllArgsConstructor
public class UserInfoValidator {
    UserInfoRepository userInfoRepository;

    public UserInfo findByLoginOrElseThrow(Authentication authentication) {
        String login = authentication.getName();
        return userInfoRepository.findByLogin(login)
                                 .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                                                                                "Sponsor with login = %s not found".formatted(
                                                                                        login)));
    }
}
