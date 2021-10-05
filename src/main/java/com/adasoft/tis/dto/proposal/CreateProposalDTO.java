package com.adasoft.tis.dto.proposal;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "CreateProposalDTO", description = "DTO para creación de una propuesta")
@Getter
@Setter
public class CreateProposalDTO extends BaseCreateDTO {
}
