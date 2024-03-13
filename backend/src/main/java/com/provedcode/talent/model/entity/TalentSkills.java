//package com.provedcode.talent.model.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.*;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Getter
//@Setter
//@Entity
//@Table(name = "talent_talents")
//public class TalentSkills {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "talent_id", updatable = false)
//    private Talent talent;
//    @Column(name = "talent_name")
//    private String talentName;
//}