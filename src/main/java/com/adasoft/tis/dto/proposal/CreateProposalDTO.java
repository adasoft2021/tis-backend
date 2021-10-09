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
    @NotNull(message = "Este campo no debe ser nulo")
    private String part;
    @Schema(
            name = "fileUrl",
            description = "Ruta de los archivos de la propuesta",
            example = "//files/company1/partA.pdf",
            type = "String"
    )
    @NotNull(message = "Este campo no debe ser nulo")
    private String fileUrl;
    @Schema(
        name = "adviserId",
        description = "id del asesor de la propuesta",
        example = "4564L",
        type = "Number"
    )
    @NotNull(message = "Este campo no debe ser nulo")
    private long adviserId;
}
