package com.adasoft.tis.dto.observation;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(name = "CreateObservationDTO", description = "DTO para creación de una observation")
@Getter
@Setter
public class CreateObservationDTO extends BaseCreateDTO {
    @Schema(
            name = "title",
            description = "titulo de una observacion de la propuesta",
            type = "String"
    )
    @NotNull
    private String title;
    @Schema(
            name = "description",
            description = "descripcion de una observacion de la propuesta",
            type = "String"
    )

    @NotNull
    private String description;
}
