package com.adasoft.tis.domain;

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
@Table(name = "companies")
public class Company extends User<Long> {
    @Column(nullable = false)
    String shortName;
    @Column(nullable = false, unique = true)
    String name;
    @Column(nullable = false)
    String companyType;
    @Column
    String address;
    @Column
    String telephone;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "partners", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "partners")
    List<String> partners;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_adviser_id", nullable = false, updatable = false)
    Adviser adviser;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_project_id")
    Project project;
}