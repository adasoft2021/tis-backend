package com.adasoft.tis.dto.proposal;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import com.adasoft.tis.domain.Proposal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(name = "CreateProposalDTO", description = "DTO para creación de una propuesta")
@Getter
@Setter
public class CreateProposalDTO extends BaseCreateDTO {
    @Schema(name = "createdById",
            description = "Empresa a la que pertenece la propuesta",
            required = true, example= "159423756456L", type = "Number")
    @NotNull(message = "Este campo no debe ser nulo")
    private long createdById;
    @Schema(
            name = "part",
            description = "Parte de la propuesta",
            example = "A",
            type = "String"
    )
    private Proposal.Part part;
    @Schema(
            name = "part",
            description = "Ruta de los archivos de la propuesta",
            example = "//files/company1/partA.pdf",
            type = "String"
    )
    private String fileUrl;
    @Schema(
        name = "reviewId",
        description = "id de la revision de la propuesta",
        example = "4564654654L",
        type = "Number"
    )
    private long reviewId;
    @Schema(
            name = "reviewId",
            description = "identificador de un asesor de la propuesta",
            example = "54L",
            type = "Number"
    )
    private long adviserId;
}
