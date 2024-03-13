package com.provedcode.talent.model.entity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "talents")
public class Talent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, insertable = false, updatable = false)
    private Long id;
    @NotEmpty
    @Column(name = "first_name", length = 20)
    private String firstName;
    @NotEmpty
    @Column(name = "last_name", length = 20)
    private String lastName;
    @NotEmpty
    @Column(name = "specialization", length = 30)
    private String specialization;
    @URL
    @Column(name = "image", length = 1000)
    private String image;
    @Column(name = "image_name", length = 100)
    private String imageName;

    @OneToOne(mappedBy = "talent", cascade = CascadeType.ALL, orphanRemoval = true)
    private TalentDescription talentDescription;
    @OneToMany(mappedBy = "talent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TalentLink> talentLinks = new ArrayList<>();
    @OneToMany(mappedBy = "talent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TalentContact> talentContacts = new ArrayList<>();
    @OneToMany(mappedBy = "talent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TalentAttachedFile> talentAttachedFiles = new ArrayList<>();
    @OneToMany(mappedBy = "talent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TalentProof> talentProofs = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "talents_skills", joinColumns = @JoinColumn(name = "talent_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new LinkedHashSet<>();
}