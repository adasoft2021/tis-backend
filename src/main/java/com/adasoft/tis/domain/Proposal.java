package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
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
    private Part part;
    @Column
    private String fileUrl;
    @OneToOne
    private Review review;
    @Column
    private long adviserId;

    public enum Part {
        A, B
    }
}

