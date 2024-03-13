package com.provedcode.talent.repo;

import com.provedcode.talent.model.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillsRepository extends JpaRepository<Skill, Long> {
    @Query("select s from Skill s where upper(s.skill) like upper(concat('%', ?1, '%'))")
    List<Skill> findBySkillContainsIgnoreCase(String skill);
}