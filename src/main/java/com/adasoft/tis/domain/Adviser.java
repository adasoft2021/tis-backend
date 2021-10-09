package com.adasoft.tis.domain;
import com.adasoft.tis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "advisers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Adviser extends BaseEntity<Long> {
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;
}
