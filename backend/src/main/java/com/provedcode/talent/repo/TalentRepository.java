package com.provedcode.talent.repo;

import com.provedcode.talent.model.entity.Talent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TalentRepository extends JpaRepository<Talent, Long> {
//    @Query("select t from Talent t left join t.skills skills where upper(skills.skill) like upper(concat('%', ?2, '%'))")
//    Page<Talent> findBySkills_SkillContainsIgnoreCase(Pageable pageable, String... skill);
//    @Query("SELECT t FROM Talent t LEFT JOIN t.skills skills WHERE UPPER(skills.skill) LIKE UPPER(CONCAT('%', :skills, '%'))") // if by-symbol contains
//    Page<Talent> findBySkills_SkillContainsIgnoreCase(Pageable pageable, @Param("skills") String... skills);
    @Query("SELECT t FROM Talent t INNER JOIN t.skills s WHERE UPPER(s.skill) IN (:filter_by)") //if only contains all
    Page<Talent> findBySkills_SkillsInIgnoreCase(Pageable pageable, @Param("filter_by") List<String> skills);
//    @Query("select t from Talent t inner join t.skills skills where upper(skills.skill) like upper(?1)")
//    Page<Talent> findBySkillsLikeIgnoreCase(String skill, Pageable pageable);

}