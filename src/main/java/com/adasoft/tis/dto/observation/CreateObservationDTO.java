package com.adasoft.tis.dto.observation;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import com.adasoft.tis.domain.Proposal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(name = "CreateObservationDTO", description = "DTO para creación de una observation")
@Getter
@Setter
public class CreateObservationDTO extends BaseCreateDTO {
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private Long proposalId;
}
