package com.adasoft.tis.dto.proposal;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalResponseDTO extends BaseResponseDTO<Long> {
    private long createdById;
    private String partA;
    private String partB;
    private long reviewId;
}
