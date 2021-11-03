package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEmbeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Embeddable
public class FileEntity extends BaseEmbeddable<Long> {
    @Column
    @NotNull
    String name;
    @Column
    @NotNull
    String url;
}
