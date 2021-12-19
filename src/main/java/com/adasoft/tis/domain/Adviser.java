package com.adasoft.tis.domain;

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
import java.util.Set;

@Data
@SuperBuilder
@EqualsAndHashCode(exclude = {"publications"}, callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "advisers")
public class Adviser extends User {
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;
    @JsonManagedReference
    @OneToMany(mappedBy = "createdBy")
    private Set<Publication> publications;

    public String getName() {
        String name = firstName + lastName;
        return name;
    }
}
