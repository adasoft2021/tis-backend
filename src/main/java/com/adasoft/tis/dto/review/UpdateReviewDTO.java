package com.adasoft.tis.dto.review;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import com.adasoft.tis.dto.qualification.UpdateQualificationDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Schema(name = "UpdateReviewDTO", description = "DTO para la actualización de una revisión")
@Getter
@Setter
public class UpdateReviewDTO extends BaseUpdateDTO<Long> {
    @Schema(
        name = "comment",
        description = "Comentario de la revisión.",
        type = "String"
    )
    private String comment;

    @Schema(
        name = "qualifications",
        description = "Colección de las calificaciones parciales.",
        required = true,
        type = "Collection"
    )
    @NotNull(message = "Este campo no debe ser nulo")
    @NotEmpty(message = "Este campo no debe estar vacío")
    @Size(min = 7, max = 7, message = "El tamaño debe ser 7")
    private Collection<UpdateQualificationDTO> qualifications;
}
