package com.adasoft.tis.dto.observation;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import com.adasoft.tis.domain.Proposal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObservationResponseDTO extends BaseResponseDTO<Long> {
    private String title;
    private String description;
    private Long proposalId;
}
