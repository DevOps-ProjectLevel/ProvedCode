package com.provedcode.sponsor.model.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.provedcode.kudos.model.entity.Kudos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sponsors")
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;
    @Column(name = "amount_kudos")
    private Long amountKudos;
    @NotEmpty
    @Column(name = "first_name", length = 20)
    private String firstName;
    @NotEmpty
    @Column(name = "last_name", length = 20)
    private String lastName;
    @URL
    @Column(name = "image", length = 1000)
    private String image;
    @Column(name = "image_name", length = 100)
    private String imageName;
    @OneToMany(mappedBy = "sponsor")
    private List<Kudos> kudoses = new ArrayList<>();
}