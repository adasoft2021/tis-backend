package com.adasoft.tis.dto.qualification;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "CreateQualificationDTO", description = "DTO para la creación de una calificación")
@Getter
@Setter
public class CreateQualificationDTO extends BaseCreateDTO {
    @JsonIgnore
    private Integer score = null;
}
