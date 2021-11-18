package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
import com.adasoft.tis.domain.proyect.Project;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "spaces")
public class Space extends BaseEntity<Long> {
    @Column(nullable = false)
    private String title;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_proyect_id", nullable = false, updatable = false)
    private Project project;

    @Column(nullable = false)
    private LocalDateTime limitDate;

    @Column(nullable = false)
    private String description;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_adviser_id", nullable = false, updatable = false)
    private Adviser createdBy;

    @Column
    private SpaceType spaceType;

    public enum SpaceType {
        ALL
    }

}
