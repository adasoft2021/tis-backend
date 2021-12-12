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
@EqualsAndHashCode(exclude = {"createdBy"}, callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "Discussions")
public class Discussion extends BaseEntity<Long> {
    @Column(nullable = false)
    private String topic;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_user_id", nullable = false, updatable = false)
    private User createdBy;
}
