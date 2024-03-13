package com.provedcode.user.service;

import com.provedcode.user.model.dto.SponsorRegistrationDTO;
import com.provedcode.user.model.dto.TalentRegistrationDTO;
import com.provedcode.user.model.dto.UserInfoDTO;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface AuthenticationService {
    UserInfoDTO login(String name, Collection<? extends GrantedAuthority> authorities);

    UserInfoDTO register(TalentRegistrationDTO user);

    UserInfoDTO register(SponsorRegistrationDTO user);

    void activateAccount(String uuid);
}
