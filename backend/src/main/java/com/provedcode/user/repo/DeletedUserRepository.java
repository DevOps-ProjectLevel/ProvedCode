package com.provedcode.user.repo;

import com.provedcode.user.model.entity.DeletedUser;
import com.provedcode.user.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeletedUserRepository extends JpaRepository<DeletedUser, Long> {
    @Query("select d from DeletedUser d where d.uuidForActivate = ?1")
    Optional<DeletedUser> findByUUID(String uuidForActivate);
    Optional<DeletedUser> findByUuidForActivate(String uuidForActivate);

}