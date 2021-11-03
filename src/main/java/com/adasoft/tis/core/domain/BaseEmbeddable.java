package com.adasoft.tis.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEmbeddable<PrimaryKey extends Serializable> {
    @Column(nullable = false)
    private boolean deleted;
}
