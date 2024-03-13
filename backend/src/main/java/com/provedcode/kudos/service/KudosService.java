package com.provedcode.kudos.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.provedcode.kudos.model.response.KudosAmountOnProofWithSponsor;
import com.provedcode.user.model.Role;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.provedcode.kudos.model.entity.Kudos;
import com.provedcode.kudos.model.request.SetAmountKudos;
import com.provedcode.kudos.model.response.KudosAmount;
import com.provedcode.kudos.model.response.KudosAmountWithSponsor;
import com.provedcode.kudos.repository.KudosRepository;
import com.provedcode.sponsor.mapper.SponsorMapper;
import com.provedcode.sponsor.model.dto.SponsorDTO;
import com.provedcode.sponsor.model.entity.Sponsor;
import com.provedcode.sponsor.repository.SponsorRepository;
import com.provedcode.talent.model.ProofStatus;
import com.provedcode.talent.model.entity.ProofSkill;
import com.provedcode.talent.model.entity.Talent;
import com.provedcode.talent.model.entity.TalentProof;
import com.provedcode.talent.repo.ProofSkillRepository;
import com.provedcode.talent.repo.TalentProofRepository;
import com.provedcode.talent.repo.TalentRepository;
import com.provedcode.user.model.entity.UserInfo;
import com.provedcode.user.repo.UserInfoRepository;

import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
@Transactional
public class KudosService {
    KudosRepository kudosRepository;
    TalentProofRepository talentProofRepository;
    UserInfoRepository userInfoRepository;
    SponsorRepository sponsorRepository;
    TalentRepository talentRepository;
    ProofSkillRepository proofSkillRepository;
    SponsorMapper sponsorMapper;

    @Transactional(readOnly = true)
    public KudosAmount getKudosForSponsor(long sponsorId, Authentication authentication) {
        String login = authentication.getName();
        UserInfo userInfo = userInfoRepository.findByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "User with login = %s not found".formatted(
                                login)));
        if (!userInfo.getSponsor().getId().equals(sponsorId)) {
            throw new ResponseStatusException(FORBIDDEN, "Only the account owner can view the number of kudos");
        }
        Sponsor sponsor = sponsorRepository.findById(sponsorId).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND,
                        String.format("Sponsor with id = %d not found", sponsorId)));
        return new KudosAmount(sponsor.getAmountKudos());
    }

    @Transactional(readOnly = true)
    public KudosAmountWithSponsor getProofAndSkillsKudos(long proofId, Authentication authentication) {
        String login = authentication.getName();
        UserInfo userInfo = userInfoRepository.findByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "User with login = %s not found".formatted(login)));
        TalentProof talentProof = talentProofRepository.findById(proofId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Proof with id = %s not found".formatted(proofId)));

        Long countOfAllKudos = talentProof.getProofSkills()
                .stream().flatMap(proofSkills -> proofSkills.getKudos()
                        .stream().map(Kudos::getAmount))
                .reduce(0L, Long::sum);
        Map<String, Map<Long, SponsorDTO>> skillsMap = new HashMap<>();

        if (userInfo.getSponsor() != null && userInfo.getTalent() == null) {
            Sponsor sponsor = sponsorRepository.findById(userInfo.getSponsor().getId())
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                            "User with login = %s not found".formatted(
                                    login)));
            talentProof.getProofSkills().forEach(proofSkill -> {
                String skill = proofSkill.getSkill().getSkill();
                Map<Long, SponsorDTO> kudosFromSponsor = talentProof.getProofSkills().stream()
                        .map(proofSkills -> {
                            proofSkills.setKudos(proofSkills.getKudos().stream()
                                    .filter(kudos -> sponsor.equals(kudos.getSponsor())).toList());
                            return proofSkills;
                        })
                        .filter(proofSkills -> proofSkills.getSkill().getSkill().equals(skill))
                        .flatMap(proofSkills -> proofSkills.getKudos().stream())
                        .collect(Collectors.toMap(
                                Kudos::getAmount,
                                proof -> proof.getSponsor() != null
                                        ? sponsorMapper.toDto(proof.getSponsor())
                                        : SponsorDTO.builder().build(),
                                (prev, next) -> next,
                                HashMap::new));
                skillsMap.put(skill, kudosFromSponsor);
            });

            return KudosAmountWithSponsor.builder()
                    .allKudosOnProof(countOfAllKudos)
                    .kudosFromSponsor(skillsMap)
                    .build();
        }

        Talent talent = talentRepository.findById(userInfo.getTalent().getId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Talent with login = %s not found".formatted(login)));
        if (talent.getId().equals(talentProof.getTalent().getId())) {
            talentProof.getProofSkills().forEach(proofSkill -> { // I dnk wtf is this piece of shit, but it works.
                String skill = proofSkill.getSkill().getSkill();
                Map<Long, SponsorDTO> kudosFromSponsor = talentProof.getProofSkills().stream()
                        .filter(proofSkills -> proofSkills.getSkill().getSkill().equals(skill))
                        .flatMap(proofSkills -> proofSkills.getKudos().stream())
                        .collect(Collectors.toMap(
                                Kudos::getAmount,
                                proof -> proof.getSponsor() != null
                                        ? sponsorMapper.toDto(proof.getSponsor())
                                        : SponsorDTO.builder().build(),
                                (prev, next) -> next,
                                HashMap::new));
                skillsMap.put(skill, kudosFromSponsor);
            });
            return KudosAmountWithSponsor.builder()
                    .allKudosOnProof(countOfAllKudos)
                    .kudosFromSponsor(skillsMap)
                    .build();
        } else {
            return KudosAmountWithSponsor.builder()
                    .allKudosOnProof(countOfAllKudos)
                    .kudosFromSponsor(null).build();
        }
    }

    public void addKudosToProof(long proofId, SetAmountKudos amountOfKudoses, Authentication authentication) {
        String login = authentication.getName();
        UserInfo userInfo = userInfoRepository.findByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "User with login = %s not found".formatted(login)));
        Sponsor sponsor = sponsorRepository.findById(userInfo.getSponsor().getId()).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND,
                        "Sponsor with login = %s not found".formatted(login)));
        TalentProof talentProof = talentProofRepository.findById(proofId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Proof with id = %d not found".formatted(proofId)));
        if (!talentProof.getStatus().equals(ProofStatus.PUBLISHED))
            throw new ResponseStatusException(FORBIDDEN,
                    "Proof that was kudosed does not have the PUBLISHED status");
        long obtainedAmount = amountOfKudoses.amount();

        if (sponsor.getAmountKudos() < obtainedAmount) {
            throw new ResponseStatusException(FORBIDDEN, "The sponsor cannot give more kudos than he has");
        }
        if (talentProof.getProofSkills().size() < 1) {
            throw new ResponseStatusException(CONFLICT, "proof doesn`t contains skills");
        }
        Long modula = obtainedAmount % talentProof.getProofSkills().size();
        if (modula != 0) {
            obtainedAmount -= modula;
        }
        sponsor.setAmountKudos(sponsor.getAmountKudos() - obtainedAmount);

        Long addKudoses = obtainedAmount / talentProof.getProofSkills().size();

        talentProof.getProofSkills().forEach(proofSkill -> {
            Kudos kudos = Kudos.builder()
                    .sponsor(sponsor)
                    .skill(proofSkill)
                    .amount(addKudoses)
                    .build();
            proofSkill.getKudos().add(kudosRepository.save(kudos));
        });
    }

    public void addKudosToSkill(long proofId, long skillId, SetAmountKudos amountOfKudos,
                                Authentication authentication) {
        String login = authentication.getName();
        UserInfo userInfo = userInfoRepository.findByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "User with login = %s not found".formatted(
                                login)));
        Sponsor sponsor = sponsorRepository.findById(userInfo.getSponsor().getId()).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND,
                        "Sponsor with login = %s not found".formatted(login)));
        TalentProof talentProof = talentProofRepository.findById(proofId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Proof with id = %d not found".formatted(proofId)));
        if (!talentProof.getStatus().equals(ProofStatus.PUBLISHED))
            throw new ResponseStatusException(FORBIDDEN,
                    "Skill on proof that was kudosed does not have the PUBLISHED status");
        long obtainedAmount = amountOfKudos.amount();
        if (sponsor.getAmountKudos() < obtainedAmount) {
            throw new ResponseStatusException(FORBIDDEN, "The sponsor cannot give more kudos than he has");
        }
        sponsor.setAmountKudos(sponsor.getAmountKudos() - obtainedAmount);
        ProofSkill proofSkill = talentProof.getProofSkills().stream()
                .filter(s -> s.getSkill().getId().equals(skillId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Skill with id = %d not found".formatted(skillId)));
        kudosRepository.save(Kudos.builder().amount(obtainedAmount).sponsor(sponsor).skill(proofSkill).build());
    }

    @Transactional(readOnly = true)
    public KudosAmount getSkillKudos(long proofId, long skillId) {
        TalentProof talentProof = talentProofRepository.findById(proofId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Proof with id = %d not found".formatted(proofId)));
        if (!talentProof.getStatus().equals(ProofStatus.PUBLISHED))
            throw new ResponseStatusException(FORBIDDEN,
                    "The skill from the proof that was referred to does not have a PUBLISHED status");
        ProofSkill proofSkill = talentProof.getProofSkills().stream()
                .filter(s -> s.getSkill().getId().equals(skillId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Skill with id = %d not found".formatted(skillId)));
        List<Kudos> kudos = kudosRepository.findBySkill(proofSkill);
        long amountOfKudos = kudos.stream().map(Kudos::getAmount).reduce(0L, Long::sum);
        return new KudosAmount(amountOfKudos);
    }

    public KudosAmountOnProofWithSponsor getProofKudos(long proofId, Authentication authentication) {
        String login = authentication.getName();
        UserInfo userInfo = userInfoRepository.findByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "User with login = %s not found".formatted(
                                login)));

        TalentProof talentProof = talentProofRepository.findById(proofId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Proof with id = %s not found".formatted(
                                proofId)));

        Long countOfAllKudos = talentProof.getProofSkills()
                .stream().flatMap(proofSkills -> proofSkills.getKudos()
                        .stream().map(Kudos::getAmount))
                .reduce(0L, Long::sum);

        //talent.getId().equals(talentProof.getTalent().getId())
        if (authentication.getAuthorities().contains(Role.SPONSOR)
                || talentProof.getTalent().equals(userInfo.getTalent())) {
            Map<Long, SponsorDTO> kudosFromSponsorTwo = new HashMap<>();
            for (ProofSkill proofSkill : talentProof.getProofSkills()) {
                for (Kudos kudos : proofSkill.getKudos()) {
                    if (kudosFromSponsorTwo.containsValue(sponsorMapper.toDto(kudos.getSponsor()))) {
                        SponsorDTO sponsor = sponsorMapper.toDto(kudos.getSponsor());
                        Map.Entry<Long, SponsorDTO> foundEntry = null;
                        for (Map.Entry<Long, SponsorDTO> entry : kudosFromSponsorTwo.entrySet()) {
                            if (entry.getValue().equals(sponsor)) {
                                foundEntry = entry;
                                break;
                            }
                        }
                        if (foundEntry != null) {
                            Long key = foundEntry.getKey();
                            SponsorDTO value = foundEntry.getValue();
                            kudosFromSponsorTwo.remove(key);
                            kudosFromSponsorTwo.put(key + kudos.getAmount(), value);
                        }
                    } else {
                        kudosFromSponsorTwo.put(kudos.getAmount(), sponsorMapper.toDto(kudos.getSponsor()));
                    }
                }
            }

            return KudosAmountOnProofWithSponsor.builder()
                    .allKudosOnProof(countOfAllKudos)
                    .kudosFromSponsor(kudosFromSponsorTwo)
                    .build();
        } else {
            return KudosAmountOnProofWithSponsor.builder()
                    .allKudosOnProof(countOfAllKudos)
                    .kudosFromSponsor(null).build();
        }
    }
}
