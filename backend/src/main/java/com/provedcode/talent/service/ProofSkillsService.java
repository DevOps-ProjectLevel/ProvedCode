package com.provedcode.talent.service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.provedcode.talent.mapper.SkillMapper;
import com.provedcode.talent.model.ProofStatus;
import com.provedcode.talent.model.dto.ProofSkillsDTO;
import com.provedcode.talent.model.dto.SkillDTO;
import com.provedcode.talent.model.dto.SkillsOnProofDTO;
import com.provedcode.talent.model.entity.ProofSkill;
import com.provedcode.talent.model.entity.Talent;
import com.provedcode.talent.model.entity.TalentProof;
import com.provedcode.talent.repo.ProofSkillRepository;
import com.provedcode.talent.repo.SkillsRepository;
import com.provedcode.talent.repo.TalentProofRepository;
import com.provedcode.talent.repo.TalentRepository;
import com.provedcode.user.model.entity.UserInfo;
import com.provedcode.user.repo.UserInfoRepository;

import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class ProofSkillsService {
    SkillsRepository skillsRepository;
    TalentRepository talentRepository;
    UserInfoRepository userInfoRepository;
    TalentProofRepository talentProofRepository;
    ProofSkillRepository proofSkillRepository;
    SkillMapper skillMapper;

    final BiConsumer<Talent, TalentProof> isValidTalentEditProof = (talent, talentProof) -> {
        if (!talent.getId().equals(talentProof.getTalent().getId())) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "talentId with id = %s and proofId with id = %s do not match"
                            .formatted(talent.getId(), talentProof.getId()));
        }
    };

    final BiConsumer<Long, UserInfo> isValidUserEditTalent = (talentId, userInfo) -> {
        if (!userInfo.getTalent().getId().equals(talentId)) {
            throw new ResponseStatusException(CONFLICT, "you can`t change another talent");
        }
    };


    public void addSkillsOnProof(long talentId, long proofId, ProofSkillsDTO skills, Authentication authentication) {
        if (!talentRepository.existsById(talentId)) {
            throw new ResponseStatusException(NOT_FOUND, "talent with id = %s not found".formatted(talentId));
        }
        UserInfo user = userInfoRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "user with id = %s not found"));
        TalentProof proof = talentProofRepository.findById(proofId).
                orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "proof with id = %s not found".formatted(proofId)));
        if (!proof.getStatus().equals(ProofStatus.DRAFT)) {
            throw new ResponseStatusException(CONFLICT, "proof status must be DRAFT");
        }
        isValidUserEditTalent.accept(talentId, user);

        // Set of new skills
        Set<Long> addedSkillsId = new TreeSet<>(skills.skills());
        addedSkillsId.forEach(skillId -> {
            if (!skillsRepository.existsById(skillId))
                throw new ResponseStatusException(NOT_FOUND, "no such skill with id = " + skillId);
        });

        // check if skill already on proof
        proof.getProofSkills().forEach(proofSkill -> {
            if (addedSkillsId.contains(proofSkill.getSkill().getId())) {
                throw new ResponseStatusException(CONFLICT,
                        "skill with id = %s already on skill".formatted(proofSkill.getSkill().getId()));
            }
        });

        Set<ProofSkill> addedSkills = new HashSet<>(skillsRepository.findAllById(addedSkillsId)).stream()
                .map(skill -> ProofSkill.builder().talentProof(proof).skill(skill).build())
                .collect(Collectors.toSet()); // skills to add on proof

        proof.getProofSkills().addAll(addedSkills);
    }

    @Transactional(readOnly = true)
    public SkillsOnProofDTO getAllSkillsOnProof(long proofId, Authentication authentication) {
        TalentProof talentProof = talentProofRepository.findById(proofId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "proof with id = %s not found".formatted(proofId)));
        Set<SkillDTO> skills = talentProof.getProofSkills().stream()
                .map(ProofSkill::getSkill)
                .map(skillMapper::skillToSkillDTO).collect(Collectors.toSet());
        if (talentProof.getStatus().equals(ProofStatus.PUBLISHED)) {
            return SkillsOnProofDTO.builder().skills(skills).build();
        } else if (authentication != null) {
            UserInfo userInfo = userInfoRepository.findByLogin(authentication.getName())
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
            if (userInfo.getTalent().getId().equals(talentProof.getTalent().getId())) {
                return SkillsOnProofDTO.builder().skills(skills).build();
            } else {
                throw new ResponseStatusException(FORBIDDEN, "you can't see proofs in DRAFT and HIDDEN status");
            }
        } else {
            throw new ResponseStatusException(FORBIDDEN, "you can't see proofs in DRAFT and HIDDEN status");
        }
    }

    public void deleteSkillOnProof(long talentId, long proofId, long skillId, Authentication authentication) {
        UserInfo userInfo = userInfoRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        isValidUserEditTalent.accept(talentId, userInfo);

        Talent talent = talentRepository.findById(talentId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "talent with id = %s not found".formatted(talentId)));

        TalentProof talentProof = talentProofRepository.findById(proofId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "proof with id = %s not found".formatted(proofId)));
        if (!talentProof.getStatus().equals(ProofStatus.DRAFT)) {
            throw new ResponseStatusException(CONFLICT, "proof status must be DRAFT");
        }
        isValidTalentEditProof.accept(talent, talentProof);

        talentProof.getProofSkills().removeIf(i -> i.getSkill().getId().equals(skillId));
    }


//    public void deleteSkillOnProof(long talentId, long proofId, long skillId, Authentication authentication) {
//        Talent talent = talentRepository.findById(talentId)
//                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
//                        "talent with id = %s not found".formatted(talentId)));
//        UserInfo userInfo = userInfoRepository.findByLogin(authentication.getName())
//                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
//        TalentProof talentProof = talentProofRepository.findById(proofId)
//                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "proof with id = %s not found".formatted(proofId)));
//        if (!talentProof.getStatus().equals(ProofStatus.DRAFT)) {
//            throw new ResponseStatusException(CONFLICT, "proof status must be DRAFT");
//        }
//        if (!talent.getId().equals(talentProof.getTalent().getId())) {
//            throw new ResponseStatusException(BAD_REQUEST,
//                    "talentId with id = %s and proofId with id = %s do not match".formatted(talentId, proofId));
//        }
//        isValidUserEditTalent.accept(talentId, userInfo);
//        Skills skills = skillsRepository.findById(skillId)
//                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
//                        "skill with id = %s not found".formatted(skillId)));
//        if (!talentProof.getSkills().contains(skills)) {
//            throw new ResponseStatusException(NOT_FOUND,
//                    "you dont have skill with id = %s on proof with id = %s".formatted(skillId, proofId));
//        }
//        talentProof.getSkills().remove(skills);
//    }
}
