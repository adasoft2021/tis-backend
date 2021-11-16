package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@EqualsAndHashCode(exclude = {"qualifications"}, callSuper = true)
@ToString(exclude = {"qualifications"}, callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review extends BaseEntity<Long> {

    @Column(nullable = false)
    private String title;

    @Column
    private String comment;

    @Column
    private Boolean published;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_adviser_id", nullable = false, updatable = false)
    private Adviser createdBy;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_company_id", nullable = false, updatable = false)
    private Company company;


    @JsonManagedReference
    @ManyToMany
    private Set<Space> spaces;

    @JsonManagedReference
    @OneToMany(mappedBy = "review")
    private Set<Qualification> qualifications = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "review")
    private Set<Observation> observations = new HashSet<>();
}
