package com.adasoft.tis.dto.proposal;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(name = "CreateProposalDTO", description = "DTO para creación de una propuesta")
@Getter
@Setter
public class CreateProposalDTO extends BaseCreateDTO {
    @Schema(name = "createById",
            description = "Empresa a la que pertenece la propuesta",
            required = true, example= "159423756456L", type = "Number")
    @NotNull(message = "Este campo no debe ser nulo")
    private long createById;
    @Schema(
        name = "partA",
        description = "Ruta de los archivos de la parte A de la propuesta",
        example = "//files/company1/partA.zip",
        type = "String"
    )
    private String partA;
    @Schema(
        name = "partB",
        description = "Ruta de los archivos de la parte B de la propuesta",
        example = "//files/company1/partB.zip",
        type = "String"
    )
    private String partB;
    @Schema(
        name = "reviewId",
        description = "id de la revision de la propuesta",
        example = "4564654654L",
        type = "Number"
    )
    private long reviewId;
}
