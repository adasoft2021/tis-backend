package com.adasoft.tis.dto.publication;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import com.adasoft.tis.domain.Publication;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Schema(name = "CreatePublicationDTO", description = "DTO para la creación de una publicación")
@Getter
@Setter
public class CreatePublicationDTO extends BaseCreateDTO {
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

    @Schema(
        name = "fileUrl",
        description = "URL del archivo de la publicación.",
        required = true,
        type = "String"
    )
    @NotNull
    private String fileUrl;

    @Schema(
        name = "type",
        description = "Tipo de la publicación.",
        required = true
    )
    @NotNull
    private Publication.PublicationType type;

    @Schema(
        name = "createdById",
        description = "ID del Asesor que está creando la publicación.",
        required = true,
        type = "Number"
    )
    @NotNull
    @Min(value = 1, message = "Este campo no puede ser menor a 1")
    private Long createdById;
}
