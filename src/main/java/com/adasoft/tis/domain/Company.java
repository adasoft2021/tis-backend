package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company extends BaseEntity<Long> {
    @Column(nullable = false)
    String shortName;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String companyType;
    @Column(nullable = false)
    String address;
    @Column(nullable = false)
    String email;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "members", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "partners")
    List<String> partners;
    @JsonManagedReference
    @OneToMany(mappedBy = "createdBy")
    Set<Publication> publications;

    public enum SocietyKind {
        ///<summary>Sociedad de responsbilidad limitada</summary>
        LLC("SRL"),
        ///<summary>Sociedad Anonima</summary>
        AC("SA"),
        ///<summary>Coorporacion</summary>
        CORP("CORPORACION"),
        ///<summary>Cooperativa</summary>
        COOP("COOPERATIVA");

        public final String type;

        SocietyKind(String type) {
            this.type = type;
        }
    }
}