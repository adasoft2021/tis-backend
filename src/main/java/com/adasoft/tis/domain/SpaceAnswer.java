package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "space_answers")
public class SpaceAnswer extends BaseEntity<Long> {
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_space_id", nullable = false, updatable = false)
    @NotNull
    Space space;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_company_id", nullable = false, updatable = false)
    @NotNull
    Company createdBy;

    @ElementCollection(fetch = FetchType.LAZY, targetClass = FileEntity.class)
    @CollectionTable(name = "files", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "files")
    @Size(min = 1)
    List<FileEntity> files;

}

