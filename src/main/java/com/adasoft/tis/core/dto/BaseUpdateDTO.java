package com.adasoft.tis.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseUpdateDTO<PrimaryKey extends Serializable> {
    @JsonIgnore
    private PrimaryKey id;
    @NotNull(message = "Este campo no debe ser nulo")
    private boolean deleted;
    @JsonIgnore
    private LocalDateTime updatedAt = LocalDateTime.now();
}
