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
        name = "part",
        description = "Parte de la propuesta",
        example = "A",
        type = "String"
    )
    private String part;
    @Schema(
        name = "fileUrl",
        description = "Ruta de los archivos de la propuesta",
        example = "//files/company1/partA.pdf",
        type = "String"
    )
    private String fileUrl;

}
