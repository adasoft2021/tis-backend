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
    @Column(nullable = false, updatable = false)
    private String description;
    @Column
    private int score;
    @Column(nullable = false, updatable = false)
    private int maxScore;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_review_id")
    private Review review;
}
