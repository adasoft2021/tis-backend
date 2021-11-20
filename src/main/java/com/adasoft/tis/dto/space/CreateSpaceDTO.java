package com.adasoft.tis.dto.space;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Schema(name = "CreateSpaceDTO", description = "DTO para la creación de un espacio")
@Getter
@Setter
public class CreateSpaceDTO extends BaseCreateDTO {
    @Schema(
        name = "title",
        description = "Título del espacio a crear.",
        required = true,
        type = "String"
    )
    @NotNull
    private String title;

    @Schema(
        name = "proyectId",
        description = "ID del proyecto con el que se crea el espacio.",
        required = true,
        type = "Long"
    )
    @NotNull
    private Long proyectId;

    @Schema(
        name = "limitDate",
        description = "Fecha del espacio.",
        required = true,
        type = "Date"
    )
    @NotNull
    private LocalDateTime limitDate;

    @Schema(
        name = "description",
        description = "descripcion de un espacio",
        type = "String"
    )
    private String description;
}