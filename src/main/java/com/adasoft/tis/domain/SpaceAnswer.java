package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "space-answers")
public class SpaceAnswer extends BaseEntity<Long> {
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_space_id", nullable = false, updatable = false)
    Space space;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_company_id", nullable = false, updatable = false)
    Company createdBy;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "files", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "files")
    List<FileEntity> files;

}

