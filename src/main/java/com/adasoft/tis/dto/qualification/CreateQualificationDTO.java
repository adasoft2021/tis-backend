package com.adasoft.tis.dto.qualification;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Schema(name = "CreateQualificationDTO", description = "DTO para la creación de una calificación")
@Getter
@Setter
public class CreateQualificationDTO extends BaseCreateDTO {
    @JsonIgnore
    private Integer score = null;
    @Schema(
        name = "description",
        description = "descripcion del Qualification a actualizar",
        required = true,
        example = "1",
        type = "Number"
    )
    @NotNull
    private String description;
    @Schema(
        name = "maxScore",
        description = "Nota maxima de la calificación parcial.",
        example = "15",
        type = "Number"
    )
    @NotNull
    @Min(value = 1, message = "Este campo no puede ser menor a 1")
    @Max(value = 100, message = "Este campo no puede ser mayor a 100")
    private int maxScore;
}
