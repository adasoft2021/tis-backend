package com.adasoft.tis.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<PrimaryKey extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private PrimaryKey id;
    @Column(nullable = false)
    private boolean deleted;
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
