package com.provedcode.talent.service;

import com.provedcode.config.EmailDefaultProps;
import com.provedcode.config.PageProperties;
import com.provedcode.config.ServerInfoConfig;
import com.provedcode.kudos.model.entity.Kudos;
import com.provedcode.talent.mapper.SkillMapper;
import com.provedcode.talent.mapper.TalentProofMapper;
import com.provedcode.talent.model.dto.*;
import com.provedcode.talent.model.entity.*;
import com.provedcode.talent.model.request.EditTalent;
import com.provedcode.talent.repo.SkillsRepository;
import com.provedcode.talent.repo.TalentProofRepository;
import com.provedcode.talent.repo.TalentRepository;
import com.provedcode.talent.utill.ValidateTalentForCompliance;
import com.provedcode.user.model.dto.SessionInfoDTO;
import com.provedcode.user.model.entity.DeletedUser;
import com.provedcode.user.model.entity.UserInfo;
import com.provedcode.user.repo.AuthorityRepository;
import com.provedcode.user.repo.DeletedUserRepository;
import com.provedcode.user.repo.UserInfoRepository;
import com.provedcode.user.service.impl.EmailService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class TalentService {
    AuthorityRepository authorityRepository;
    TalentProofRepository talentProofRepository;
    TalentRepository talentRepository;
    UserInfoRepository userInfoRepository;
    PageProperties pageProperties;
    ValidateTalentForCompliance validateTalentForCompliance;
    SkillsRepository skillsRepository;
    TalentProofMapper talentProofMapper;
    DeletedUserRepository deletedUserRepository;
    ServerInfoConfig serverInfoConfig;
    EmailService emailService;
    EmailDefaultProps emailDefaultProps;
    SkillMapper skillMapper;

    @Transactional(readOnly = true)
    public Page<Talent> getTalentsPage(Integer page, Integer size) {
        return talentRepository.findAll(PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    public Talent getTalentById(long id) {
        return talentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    public Talent editTalent(long id, EditTalent editTalent, Authentication authentication) {
        Optional<Talent> talent = talentRepository.findById(id);
        Optional<UserInfo> userInfo = userInfoRepository.findByLogin(authentication.getName());
        validateTalentForCompliance.userVerification(talent, userInfo, id);
        checkEditTalentNull(editTalent);

        Talent editableTalent = talent.get();

        TalentDescription editableTalentDescription = editableTalent.getTalentDescription();
//        List<TalentSkills> editableTalentTalents = editableTalent.getTalentTalents();
        List<TalentLink> editableTalentLinks = editableTalent.getTalentLinks();
        List<TalentContact> editableTalentContacts = editableTalent.getTalentContacts();
        List<TalentAttachedFile> editableTalentAttachedFiles = editableTalent.getTalentAttachedFiles();

        if (editTalent.firstName() != null) {
            editableTalent.setFirstName(editTalent.firstName());
        }
        if (editTalent.lastName() != null) {
            editableTalent.setLastName(editTalent.lastName());
        }
        if (editTalent.specialization() != null) {
            editableTalent.setSpecialization(editTalent.specialization());
        }
        if (editTalent.image() != null) {
            editableTalent.setImage(editTalent.image());
        }
        if (editTalent.additionalInfo() != null || editTalent.bio() != null) {
            if (editableTalentDescription != null) {
                if (editTalent.additionalInfo() != null)
                    editableTalentDescription.setAdditionalInfo(editTalent.additionalInfo());
                if (editTalent.bio() != null)
                    editableTalentDescription.setBio(editTalent.bio());
            } else {
                editableTalentDescription = TalentDescription.builder()
                        .additionalInfo(editTalent.additionalInfo())
                        .bio(editTalent.bio())
                        .talent(editableTalent)
                        .build();
            }
            editableTalent.setTalentDescription(editableTalentDescription);
        }
//        if (editTalent.talents() != null) {
//            editableTalentTalents.clear();
//            editableTalentTalents.addAll(editTalent.talents().stream().map(s -> TalentSkills.builder()
//                                                                                             .talent(editableTalent)
//                                                                                             .talentName(s)
//                                                                                             .build()).toList());
//            editableTalent.setTalentTalents(editableTalentTalents);
//        }
        if (editTalent.links() != null) {
            editableTalentLinks.clear();
            editableTalentLinks.addAll(editTalent.links().stream().map(s -> TalentLink.builder()
                    .talent(editableTalent)
                    .link(s)
                    .build()).toList());
            editableTalent.setTalentLinks(editableTalentLinks);
        }
        if (editTalent.contacts() != null) {
            editableTalentContacts.clear();
            editableTalentContacts.addAll(editTalent.contacts().stream().map(s -> TalentContact.builder()
                    .talent(editableTalent)
                    .contact(s)
                    .build()).toList());
            editableTalent.setTalentContacts(editableTalentContacts);
        }
        if (editTalent.attachedFiles() != null) {
            editableTalentAttachedFiles.clear();
            editableTalentAttachedFiles.addAll(editTalent.attachedFiles().stream().map(s -> TalentAttachedFile.builder()
                            .talent(editableTalent)
                            .attachedFile(
                                    s)
                            .build())
                    .toList());
            editableTalent.setTalentAttachedFiles(editableTalentAttachedFiles);
        }
        return talentRepository.save(editableTalent);
    }

    public SessionInfoDTO deleteTalentById(long id, Authentication authentication) {
        Optional<Talent> talent = talentRepository.findById(id);
        Optional<UserInfo> userInfo = userInfoRepository.findByLogin(authentication.getName());

        validateTalentForCompliance.userVerification(talent, userInfo, id);

        UserInfo user = userInfo.get();
        userInfoRepository.delete(user);

        return new SessionInfoDTO("deleted", "null");
    }

    public void deactivateTalentById(long id, Authentication authentication) {
        Optional<Talent> talent = talentRepository.findById(id);
        Optional<UserInfo> userInfo = userInfoRepository.findByLogin(authentication.getName());

        validateTalentForCompliance.userVerification(talent, userInfo, id);

        UserInfo user = userInfo.get();
        user.setIsLocked(true);
        DeletedUser deletedUser = DeletedUser.builder()
                .deletedUser(user)
                .timeToDelete(Instant.now().plus(3, ChronoUnit.DAYS))
                .uuidForActivate(UUID.randomUUID().toString())
                .build();
        deletedUserRepository.save(deletedUser);
        userInfoRepository.save(user);

        String userActivateAccountLink = serverInfoConfig.getFullServerAddress() + "/api/v5/activate?uuid=" + deletedUser.getUuidForActivate();

        emailService.sendEmail(user.getLogin(),
                emailDefaultProps.userDeletedSubject(),
                emailDefaultProps.userDeleted().formatted(userActivateAccountLink));
    }

    private void checkEditTalentNull(EditTalent editTalent) {
        if (editTalent.firstName() == null && editTalent.lastName() == null && editTalent.image() == null &&
                editTalent.specialization() == null && editTalent.additionalInfo() == null && editTalent.bio() == null &&
                editTalent.links() == null && editTalent.contacts() == null && editTalent.attachedFiles() == null)
            throw new ResponseStatusException(BAD_REQUEST, "you did not provide information to make changes");
    }

    public void addSkillOnTalent(long id, SkillIdDTO skillIdDTO, Authentication authentication) {
        Optional<Talent> talent = talentRepository.findById(id);
        Optional<UserInfo> userInfo = userInfoRepository.findByLogin(authentication.getName());
        validateTalentForCompliance.userVerification(talent, userInfo, id);
        Talent talentObject = talent.get();

        Set<Skill> skillsFromRepo = skillIdDTO.id().stream()
                .map(element -> skillsRepository.findById(element)
                        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                                "Skill with id = %d not found".formatted(element))))
                .collect(Collectors.toSet());

        Set<Skill> skillsFromProofs = talentObject.getTalentProofs().stream()
                .flatMap(talentProof -> talentProof.getProofSkills()
                        .stream().map(skill -> skill.getSkill())).collect(Collectors.toSet());

        for (Skill skill : talentObject.getSkills()) {
            for (Skill skillForAdd : skillsFromRepo) {
                if (skill.equals(skillForAdd)) {
                    throw new ResponseStatusException(CONFLICT,
                            "Skill with id = %d found in talent's skills".formatted(skill.getId()));
                }
            }
        }
        skillsFromRepo.stream()
                .filter(skill -> !skillsFromProofs.contains(skill))
                .findFirst()
                .ifPresent(skill -> {
                    throw new ResponseStatusException(FORBIDDEN,
                            "Skill with id = %d not found in talent's proofs".formatted(skill.getId()));
                });

        skillsFromRepo.stream()
                .forEach(skill -> talentObject.getSkills().add(skill));
    }

    public void deleteSkillFromTalent(long talentId, long skillId, Authentication authentication) {
        Optional<Talent> talent = talentRepository.findById(talentId);
        Optional<UserInfo> userInfo = userInfoRepository.findByLogin(authentication.getName());
        Skill skill = skillsRepository.findById(skillId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Skill with id = %d not found".formatted(skillId)));
        validateTalentForCompliance.userVerification(talent, userInfo, talentId);
        Talent talentObject = talent.get();
        if (!talentObject.getSkills().remove(skill)) {
            throw new ResponseStatusException(NOT_FOUND,
                    "Skill with id = %d not found".formatted(skillId));
        }
    }

    @Transactional(readOnly = true)
    public Page<Talent> getFilteredBySkillsTalentsPage(@PositiveOrZero Integer page,
                                                       @Min(1) @Max(1000) Integer size,
                                                       String... filterBy) {
        return filterBy != null ?
                talentRepository.findBySkills_SkillsInIgnoreCase(PageRequest.of(page, size), Arrays.stream(filterBy).map(String::toUpperCase).toList())
                : talentRepository.findAll(PageRequest.of(page, size));
    }

    public StatisticsDTO getStatisticsForTalent(long talentId, Authentication authentication) {
        Optional<Talent> talent = talentRepository.findById(talentId);
        Optional<UserInfo> userInfo = userInfoRepository.findByLogin(authentication.getName());
        validateTalentForCompliance.userVerification(talent, userInfo, talentId);
        Talent talentObject = talent.get();
        return StatisticsDTO.builder()
                .allKudosOnTalent(getAllKudosOnTalent(talentObject))
                .skillWithLargestNumberOfKudos(getSkillWithLargestNumberOfKudos(talentObject))
                .proofWithLargestNumberOfKudos(getProofWithLargestNumberOfKudos(talentObject))
                .build();
    }

    public Long getAllKudosOnTalent(Talent talent) {
        return talent.getTalentProofs()
                .stream()
                .flatMap(x -> x.getProofSkills().stream())
                .flatMap(y -> y.getKudos().stream())
                .mapToLong(q -> q.getAmount())
                .sum();
    }

    public Map<String, Long> getSkillWithLargestNumberOfKudos(Talent talent) {
        Map<String, Long> numberKudosOnSkill = new HashMap<>();
        for (TalentProof talentProof : talent.getTalentProofs()) {
            for (ProofSkill proofSkill : talentProof.getProofSkills()) {
                for (Kudos kudos : proofSkill.getKudos()) {
                    if (numberKudosOnSkill.containsKey(proofSkill.getSkill().getSkill())) {
                        Long amountKudosOnSkill = numberKudosOnSkill.get(proofSkill.getSkill().getSkill());
                        numberKudosOnSkill.remove(proofSkill.getSkill().getSkill());
                        numberKudosOnSkill.put(proofSkill.getSkill().getSkill(), amountKudosOnSkill + kudos.getAmount());
                    } else {
                        numberKudosOnSkill.put(proofSkill.getSkill().getSkill(), kudos.getAmount());
                    }
                }
            }
        }
        Long max = Collections.max(numberKudosOnSkill.values());
        Map<String, Long> result = new HashMap<>();
        for (Map.Entry<String, Long> entry : numberKudosOnSkill.entrySet()) {
            if (entry.getValue().equals(max)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

    public Map<ProofDTO, Long> getProofWithLargestNumberOfKudos(Talent talent) {
        Map<ProofDTO, Long> result = new HashMap<>();
        Long maxForResult = 0L;
        for (TalentProof talentProof : talent.getTalentProofs()) {
            Long amountKudosOnSkills = 0L;
            for (ProofSkill proofSkill : talentProof.getProofSkills()) {
                for (Kudos kudos : proofSkill.getKudos()) {
                    amountKudosOnSkills += kudos.getAmount();
                }
            }
            if (amountKudosOnSkills > maxForResult) {
                maxForResult = amountKudosOnSkills;
                result.clear();
                result.put(talentProofMapper.toProofDTO(talentProof), amountKudosOnSkills);
            }
        }
        return result;
    }

    public List<SkillDTO> getAllSkillsOnTalentsProofs(long talentId, Authentication authentication) {
        Optional<Talent> talent = talentRepository.findById(talentId);
        Optional<UserInfo> userInfo = userInfoRepository.findByLogin(authentication.getName());
        validateTalentForCompliance.userVerification(talent, userInfo, talentId);
        Talent talentObject = talent.get();
        return talentObject.getTalentProofs().stream()
                .flatMap(x -> x.getProofSkills()
                        .stream()
                        .map(y -> skillMapper.skillToSkillDTO(y.getSkill()))).collect(Collectors.toList());
    }
}