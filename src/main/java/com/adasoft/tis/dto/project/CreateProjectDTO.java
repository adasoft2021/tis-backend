package com.adasoft.tis.dto.project;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(name = "CreateProjectDTO", description = "DTO para la creación de un proyecto")
@Getter
@Setter
public class CreateProjectDTO extends BaseCreateDTO {
    @Schema(
        name = "title",
        description = "Título de la publicación a crear.",
        required = true,
        type = "String"
    )
    @NotNull
    @Size(min = 15, max = 70, message = "Este campo debe tener entre 15 y 70 caracteres")
    private String title;

    @Schema(
        name = "announcementId",
        description = "ID de la convocatoria que está relacionada con el proyecto.",
        required = true,
        type = "Number"
    )
    @NotNull
    @Min(value = 1, message = "Este campo no puede ser menor a 1")
    private Long createannouncementId;

    @Schema(
        name = "specificationSheetId",
        description = "ID del Pliego de Especificaciones relacionado al proyecto.",
        required = true,
        type = "Number"
    )
    @NotNull
    @Min(value = 1, message = "Este campo no puede ser menor a 1")
    private Long specificationSheetId;
}
