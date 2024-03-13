package com.provedcode.user.util;

import com.provedcode.user.repo.DeletedUserRepository;
import com.provedcode.user.repo.UserInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Service
@EnableScheduling
@AllArgsConstructor
@Transactional
@Slf4j
public class UsersSchedulerService {
    UserInfoRepository userInfoRepository;
    DeletedUserRepository deletedUsersRepository;

    @Scheduled(timeUnit = TimeUnit.HOURS, fixedRate = 1)
    void deleteUser() {
        deletedUsersRepository.findAll()
                .forEach(userToDelete -> {
                    if (userToDelete.getTimeToDelete().isAfter(Instant.now())) {
                        log.info("deleting user: id = {}", userToDelete.getDeletedUser().getId());
                        deletedUsersRepository.delete(userToDelete);
                        userInfoRepository.delete(userToDelete.getDeletedUser());
                    }
                });
    }

//    @Async
//    public void scheduleDeletingMethod(String uuid) {
//        try {
//            TimeUnit.MINUTES.sleep(3);
//            log.info("scheduleDeletingMethod = {}", uuid);
//            deletedUsersRepository.findByUUID(uuid)
//                    .ifPresent(deleted -> {
//                        deletedUsersRepository.delete(deleted);
//                        deleteUser(deleted.getDeletedUser());
//                    });
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//
//    private void deleteUser(UserInfo user) {
//        if (user.getSponsor() != null) {
//            List<Kudos> kudosList = user.getSponsor().getKudoses().stream().map(i -> {
//                i.setSponsor(null);
//                return i;
//            }).toList();
//            user.getSponsor().setKudoses(kudosList);
//            userInfoRepository.delete(user);
//        }
//        if (user.getTalent() != null) {
//            userInfoRepository.delete(user);
//        }
//    }
}
