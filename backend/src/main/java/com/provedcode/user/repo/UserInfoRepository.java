package com.provedcode.user.repo;

import com.provedcode.user.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    long deleteByTalentId(Long talentId);
    boolean existsByLogin(String login);
    Optional<UserInfo> findByLogin(String login);

}