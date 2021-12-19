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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@EqualsAndHashCode(exclude = {"qualifications", "observations", "spaces"}, callSuper = true)
@ToString(exclude = {"qualifications"}, callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "reviews")

public class Review extends BaseEntity<Long> {

    @Column
    private String comment;

    @Column(nullable = false)
    private Review.Status status;

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

    public boolean isPublished() {
        return status.ordinal() > Status.QUALIFIED.ordinal();
    }

    public enum Status {
        UNREVIEWED("Sin Revisar"),
        REVIEWED("Revisada"),
        QUALIFIED("Calificada"),
        /// final states ///
        IN_CHANGE_ORDER("En Orden de Cambio"),
        IN_PROPOSAL_ACCEPTANCE("En Aceptacion de Propuesta"),
        IN_ADDENDUM("En Adenda");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public static List<Status> finalValues() {
            return Arrays.stream(values())
                .dropWhile(s -> !s.equals(Review.Status.IN_CHANGE_ORDER)).collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
