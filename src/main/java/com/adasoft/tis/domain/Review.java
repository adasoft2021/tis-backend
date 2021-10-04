package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review extends BaseEntity<Long> {
    @Column(nullable = false)
    private int score;
    @Column
    private String comment;
    @Column(nullable = false)
    private long createdBy;
}
