package com.provedcode.kudos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.provedcode.kudos.model.entity.Kudos;
import com.provedcode.talent.model.entity.ProofSkill;


public interface KudosRepository extends JpaRepository<Kudos, Long> {
    List<Kudos> findBySkill(ProofSkill skill);
}