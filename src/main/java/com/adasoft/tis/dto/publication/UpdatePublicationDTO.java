package com.adasoft.tis.dto.publication;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Schema(name = "UpdatePublicationDTO", description = "DTO para la actualización de una publicación")
@Getter
@Setter
public class UpdatePublicationDTO extends BaseUpdateDTO<Long> {
    @Schema(
        name = "title",
        description = "Título de la publicación a actualizar.",
        required = true,
        type = "String"
    )
    @NotNull
    @Size(min = 15, max = 70, message = "Este campo debe tener entre 15 y 70 caracteres")
    private String title;

    @Schema(
        name = "date",
        description = "Fecha para la publicación.",
        required = true,
        type = "Date"
    )
    @NotNull
    private LocalDateTime date;

    @Schema(
        name = "code",
        description = "Código de la publicación.",
        required = true,
        type = "String"
    )
    @NotNull
    @Size(min = 10, max = 20, message = "Este campo debe tener entre 10 y 20 caracteres")
    private String code;

    @Schema(
        name = "semester",
        description = "Semestre de la publicación.",
        required = true,
        type = "String"
    )
    @NotNull
    @Size(min = 6, max = 6, message = "Este campo debe tener 6 caracteres")
    private String semester;
}
