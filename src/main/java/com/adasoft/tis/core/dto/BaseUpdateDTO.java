package com.adasoft.tis.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseUpdateDTO<PrimaryKey extends Serializable> {
    @JsonIgnore
    private PrimaryKey id;
    @JsonIgnore
    private LocalDateTime updatedAt = LocalDateTime.now();
}
