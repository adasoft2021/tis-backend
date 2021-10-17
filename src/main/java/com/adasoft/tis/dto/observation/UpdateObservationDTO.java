package com.adasoft.tis.dto.observation;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(name = "UpdateProposalDTO", description = "DTO para la actualización de una propuesta")
@Getter
@Setter
public class UpdateObservationDTO extends BaseUpdateDTO<Long> {

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
