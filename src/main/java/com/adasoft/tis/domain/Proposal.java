package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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
    private String partA;
    @Column
    private String partB;
    @Column
    private long review;
}
