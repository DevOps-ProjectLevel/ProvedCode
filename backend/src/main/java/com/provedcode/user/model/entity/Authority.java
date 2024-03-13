package com.provedcode.user.model.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import com.provedcode.user.model.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotEmpty
    @NotNull
    @Column(name = "authority", length = 20)
    private Role authority;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authorities")
    private Set<UserInfo> userInfoes = new LinkedHashSet<>();
}