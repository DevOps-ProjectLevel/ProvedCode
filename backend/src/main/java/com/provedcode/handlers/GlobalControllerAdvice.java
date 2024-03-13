package com.provedcode.handlers;

import com.provedcode.user.model.entity.UserInfo;
import com.provedcode.user.repo.UserInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalControllerAdvice {
    UserInfoRepository userInfoRepository;

    @ModelAttribute
    public void handleAuthentication(Authentication authentication) {
        if (authentication != null) {
            String login = authentication.getName();
            UserInfo user = userInfoRepository.findByLogin(login)
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                            "User with login {%s} not found".formatted(login)));
            if (Boolean.TRUE.equals(user.getIsLocked())) {
                throw new ResponseStatusException(FORBIDDEN, "your account is blocked");
            }
        }
    }
}
