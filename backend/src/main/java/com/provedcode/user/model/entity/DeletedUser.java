package com.provedcode.user.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Slf4j
@Builder
@Entity
public class DeletedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant timeToDelete;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserInfo deletedUser;
    private String uuidForActivate;
}