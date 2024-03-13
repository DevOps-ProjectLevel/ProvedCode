package com.provedcode.aws.scheduler;

import com.provedcode.aws.service.FileService;
import com.provedcode.talent.repo.TalentRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@AllArgsConstructor
public class UpdateScheduler {
    FileService fileService;
    TalentRepository talentRepository;
    
    @Scheduled(cron = "0 0 13 * * SAT")
    public void updateTalentsImages() {

        talentRepository.findAll().stream()
                .map(i -> {
                    if (i.getImageName() != null) {
                        i.setImage(fileService.generetePresingedUrlFor7Days(i.getImageName()).toString());
                    }
                    return i;
                }).forEach(talentRepository::save);
    }
    
}