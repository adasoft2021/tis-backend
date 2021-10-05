package com.adasoft.tis.dto.proposal;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "UpdateProposalDTO", description = "DTO para la actualización de una propuesta")
@Getter
@Setter
public class UpdateProposalDTO extends BaseUpdateDTO<Long> {
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
}
