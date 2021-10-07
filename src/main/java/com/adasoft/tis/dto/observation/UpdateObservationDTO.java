package com.adasoft.tis.dto.observation;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import com.adasoft.tis.domain.Proposal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(name = "UpdateProposalDTO", description = "DTO para la actualización de una propuesta")
@Getter
@Setter
public class UpdateObservationDTO extends BaseUpdateDTO<Long> {
    @NotNull
    private String title;
    @NotNull
    private String  description;
}
