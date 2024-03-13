package com.provedcode.user.repo;

import com.provedcode.user.model.Role;
import com.provedcode.user.model.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthority(Role authority);
}