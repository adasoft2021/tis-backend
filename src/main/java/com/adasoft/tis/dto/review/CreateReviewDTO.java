package com.adasoft.tis.dto.review;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Schema(name = "CreateReviewDTO", description = "DTO para la creación de una revisión")
@Getter
@Setter
public class CreateReviewDTO extends BaseCreateDTO {
    @JsonIgnore
    private Integer totalScore = null;
    @JsonIgnore
    private String comment = null;
    @Schema(
        name = "createdById",
        description = "ID del Asesor que creó la revisión.",
        required = true,
        example = "1",
        type = "Number"
    )
    @NotNull(message = "Este campo no debe ser nulo")
    @Min(value = 1, message = "Este campo no puede ser menor a 1")
    private Long createdById;
}
