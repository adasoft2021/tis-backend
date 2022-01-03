package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true, exclude = {"observations", "spaceAnswer"})
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "files")
public class File extends BaseEntity<Long> {
    @Column
    @NotNull
    String name;
    @Column
    @NotNull
    String url;
    @Column
    @NotNull
    boolean reviewed;

    @JsonManagedReference
    @OneToMany(mappedBy = "file")
    private Set<Observation> observations = new HashSet<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_space_answer_id", nullable = false, updatable = false)
    private SpaceAnswer spaceAnswer;
}
