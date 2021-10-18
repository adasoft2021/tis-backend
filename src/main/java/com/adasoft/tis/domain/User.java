package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User<PrimaryKey extends Serializable> extends BaseEntity<PrimaryKey> {
    @Column(nullable = false, unique = true, updatable = true)
    String userName;
    @Column(nullable = false)
    String password;
}
