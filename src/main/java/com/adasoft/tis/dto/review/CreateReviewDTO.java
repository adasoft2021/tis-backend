package com.adasoft.tis.dto.review;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Schema(name = "CreateReviewDTO", description = "DTO para la creación de una revisión")
@Getter
@Setter
public class CreateReviewDTO extends BaseCreateDTO {
    @Schema(name = "score", description = "Nota de la revisión.", required = true, example = "100", type = "Number")
    @NotNull(message = "Este campo no debe ser nulo")
    @Min(value = 0, message = "Este campo no debe ser menor a 0")
    @Max(value = 100, message = "Este campo no debe ser mayor a 100")
    private int score;
    @Schema(
        name = "comment",
        description = "Comentario de la revisión.",
        example = "Este es un comentario.",
        type = "String"
    )
    private String comment = null;
    @Schema(
        name = "createdBy",
        description = "ID del Asesor que creó la revisión.",
        required = true,
        example = "1",
        type = "Number"
    )
    @NotNull(message = "Este campo no debe ser nulo.")
    private long createdBy;
}
