package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;


@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "proposals")
public class Proposal extends BaseEntity<Long> {
    @Column(nullable = false)
    private long createdBy;
    @Column
    private String part;
    @Column
    private String fileUrl;
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "fk_review_id", nullable = true, updatable = false)
    private Review review;
    @Column
    private long adviser;
}

