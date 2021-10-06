package com.adasoft.tis.dto.proposal;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import com.adasoft.tis.domain.Proposal;
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
    private Proposal.Part part;

    private String fileUrl;

    private long reviewId;
    private long adviserId;
}
