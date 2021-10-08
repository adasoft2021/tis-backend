package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@EqualsAndHashCode(exclude = {"review", "baseQualification"}, callSuper = true)
@ToString(exclude = {"review", "baseQualification"}, callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "qualifications")
public class Qualification extends BaseEntity<Long> {
    @Column
    private Integer score;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_review_id", nullable = false, updatable = false)
    private Review review;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_base_qualification_id", nullable = false, updatable = false)
    private BaseQualification baseQualification;
}
