package com.provedcode.talent.model.entity;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "skill", length = 30)
    private String skill;
    @ManyToMany(mappedBy = "skills")
    private Set<Talent> talents = new LinkedHashSet<>();
    @ManyToMany(mappedBy = "skill")
    private Set<ProofSkill> proofSkills = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Skill skills = (Skill) o;
        return getId() != null && Objects.equals(getId(), skills.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}