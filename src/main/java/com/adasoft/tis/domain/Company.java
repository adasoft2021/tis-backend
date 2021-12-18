package com.adasoft.tis.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true, exclude = {"partners", "adviser", "project", "assigned", "semester"})
@ToString(callSuper = true, exclude = {"partners", "adviser", "project", "assigned", "semester"})
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company extends User {
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
    @JsonManagedReference
    @OneToMany(mappedBy = "company")
    Set<Partner> partners;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_adviser_id", nullable = false, updatable = false)
    Adviser adviser;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_project_id")
    Project project;
    @JsonManagedReference
    @OneToMany(mappedBy = "company")
    private Set<CompanySpace> assigned;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_semester_id")
    Semester semester;
}