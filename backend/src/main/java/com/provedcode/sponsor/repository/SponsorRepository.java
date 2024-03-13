package com.provedcode.sponsor.repository;

import com.provedcode.sponsor.model.entity.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
}