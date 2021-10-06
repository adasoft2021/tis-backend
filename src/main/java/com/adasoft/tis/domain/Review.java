package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@EqualsAndHashCode(exclude = {"qualifications"}, callSuper = true)
@ToString(exclude = {"qualifications"}, callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review extends BaseEntity<Long> {
    @Column
    private int totalScore;
    @Column
    private String comment;
    @Column(nullable = false, updatable = false)
    private long createdBy;

    @JsonManagedReference
    @OneToMany(mappedBy = "review")
    private Set<Qualification> qualifications = new HashSet<>();
}
