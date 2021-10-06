package com.adasoft.tis.dto.proposal;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import com.adasoft.tis.domain.Proposal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalResponseDTO extends BaseResponseDTO<Long> {
    private long createdById;
    private Proposal.Part part;
    private String fileUrl;
    private long reviewId;
    private long adviserId;
}
