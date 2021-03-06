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
@EqualsAndHashCode(callSuper = true, exclude = {"review", "file"})
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "observations")
public class Observation extends BaseEntity<Long> {
    @Column(nullable = false)
    String title;
    @Column(nullable = false, length = 800)
    String description;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_review_id", nullable = false, updatable = false)
    Review review;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_file_id", nullable = false, updatable = false)
    File file;

}
