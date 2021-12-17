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
@EqualsAndHashCode(callSuper = true, exclude = {"company"})
@ToString(callSuper = true, exclude = {"company"})
@NoArgsConstructor
@Entity
@Table(name = "partners")
public class Partner extends BaseEntity<Long> {
    @Column(nullable = false, updatable = false)
    String name;
    @Column(unique = true)
    String email;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_company_id", nullable = false, updatable = false)
    private Company company;

}
