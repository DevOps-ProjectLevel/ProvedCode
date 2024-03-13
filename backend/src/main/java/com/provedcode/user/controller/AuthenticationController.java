package com.provedcode.user.controller;

import com.provedcode.talent.service.TalentService;
import com.provedcode.user.model.dto.SponsorRegistrationDTO;
import com.provedcode.user.model.dto.TalentRegistrationDTO;
import com.provedcode.user.model.dto.UserInfoDTO;
import com.provedcode.user.service.AuthenticationService;
import com.provedcode.util.annotations.doc.controller.user.PostSponsorRegistrationApiDoc;
import com.provedcode.util.annotations.doc.controller.user.PostTalentRegistrationApiDoc;
import com.provedcode.util.annotations.doc.controller.user.PostUserLoginApiDoc;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostUserLoginApiDoc
    @PostMapping("/v2/login")
    UserInfoDTO login(Authentication authentication) {
        return authenticationService.login(authentication.getName(), authentication.getAuthorities());
    }

    @PostTalentRegistrationApiDoc
    @PostMapping("/v2/talents/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserInfoDTO register(@RequestBody @Valid TalentRegistrationDTO user) {
        return authenticationService.register(user);
    }

    @PostSponsorRegistrationApiDoc
    @PostMapping("/v3/sponsors/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserInfoDTO register(@RequestBody @Valid SponsorRegistrationDTO user) {
        return authenticationService.register(user);
    }

    @GetMapping("/v5/activate")
    void activateAccount(@RequestParam("uuid") String uuid) {
        authenticationService.activateAccount(uuid);
    }
}