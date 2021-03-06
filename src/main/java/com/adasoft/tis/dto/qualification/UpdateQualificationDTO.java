package com.adasoft.tis.dto.qualification;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Schema(name = "UpdateQualificationDTO", description = "DTO para la actualización de una calificación")
@Getter
@Setter
public class UpdateQualificationDTO extends BaseUpdateDTO<Long> {
    @Schema(
        name = "score",
        description = "Nota de la calificación parcial.",
        example = "15",
        type = "Number"
    )
    @Min(value = 0, message = "Este campo no puede ser menor a 0")
    private Integer score;

    @Schema(
        name = "qualificationId",
        description = "ID del Qualification a actualizar",
        required = true,
        example = "1",
        type = "Number"
    )
    @NotNull(message = "Este campo de debe ser nulo")
    @Min(value = 1, message = "Este campo no puede ser menor a 1")
    private Long qualificationId;
}
