package com.adasoft.tis.dto.advertisement;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(name = "CreateAdvertisementDTO", description = "DTO para crear anuncios")
public class CreateAdvertisementDTO extends BaseCreateDTO {
    @Schema(
        name = "title",
        description = "Titulo de anuncio a crear.",
        required = true,
        type = "String"
    )
    @NotNull
    @Size(max = 70, message = "Este campo debe tener 70 caracteres")
    private String title;
    @Schema(
        name = "description",
        description = "Descripcion de anuncio a crear.",
        required = true,
        type = "String"
    )
    @NotNull
    private String description;
    @NotNull
    @Min(value = 1, message = "Este campo no puede ser menor a 1")
    private Long createdById;
}
