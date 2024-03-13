package com.provedcode.talent.utill;

import com.provedcode.talent.model.entity.Talent;
import com.provedcode.talent.model.entity.TalentProof;
import com.provedcode.user.model.entity.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ValidateTalentForCompliance {

    public void userVerification(Optional<Talent> talent, Optional<UserInfo> userInfo, long id) {
        if (talent.isEmpty() || userInfo.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, String.format("talent with id = %d not found", id));
        }
        if (userInfo.get().getTalent().getId() != id) {
            throw new ResponseStatusException(FORBIDDEN);
        }
    }

    public void userAndProofVerification(Optional<Talent> talent,
                                         Optional<TalentProof> talentProof,
                                         Optional<UserInfo> userInfo, long talentId, long proofId) {
        if (talent.isEmpty() && userInfo.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, String.format("talent with id = %d not found", talentId));
        }
        if (userInfo.get().getTalent().getId() != talentId) {
            throw new ResponseStatusException(FORBIDDEN, "you can`t delete/update another user");
        }
        if (talentProof.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, String.format("proof with id = %d not found", proofId));
        }
        if (talentProof.get().getTalent().getId() != talentId) {
            throw new ResponseStatusException(FORBIDDEN, "you can`t delete/update another proof");
        }
    }
}
