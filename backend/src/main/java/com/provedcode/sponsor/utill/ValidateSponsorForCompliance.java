package com.provedcode.sponsor.utill;

import com.provedcode.sponsor.model.entity.Sponsor;
import com.provedcode.user.model.entity.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ValidateSponsorForCompliance {

    public void userVerification(Optional<Sponsor> sponsor, Optional<UserInfo> userInfo, long sponsorId) {
        if (sponsor.isEmpty() || userInfo.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, String.format("sponsor with id = %d not found", sponsorId));
        }
        if (userInfo.get().getSponsor().getId() != sponsorId) {
            throw new ResponseStatusException(FORBIDDEN);
        }
    }
}
