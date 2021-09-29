package com.adasoft.tis.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseCreateDTO {
    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();
    @JsonIgnore
    private LocalDateTime updatedAt = LocalDateTime.now();
    @JsonIgnore
    private boolean deleted = false;
}
