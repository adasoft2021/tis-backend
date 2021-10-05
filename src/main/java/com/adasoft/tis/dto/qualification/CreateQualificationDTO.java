package com.adasoft.tis.dto.qualification;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(name = "CreateQualificationDTO", description = "DTO para la creación de una calificación")
@Getter
@Setter
public class CreateQualificationDTO extends BaseCreateDTO {
    @Schema(
        name = "description",
        description = "Descripción de la calificación parcial.",
        required = true, example = "Cumplimiento de especificaciones del proponente",
        type = "String"
    )
    @NotBlank(message = "Este campo no puede ser nulo")
    @Size(min = 10)
    private String description;
    @Schema(
        name = "score",
        description = "Nota de la calificación parcial.",
        example = "14",
        type = "Number"
    )
    @Min(value = 0, message = "Este campo no puede ser menor a 0")
    private Integer score;
    @Schema(
        name = "maxScore",
        description = "Nota máxima parcial.",
        required = true,
        example = "15",
        type = "Number"
    )
    @NotNull(message = "Este campo no puede ser nulo")
    private Integer maxScore;
}
