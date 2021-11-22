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
@EqualsAndHashCode(exclude = {"review"}, callSuper = true)
@ToString(exclude = {"review"}, callSuper = true)
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
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int maxScore;
}
