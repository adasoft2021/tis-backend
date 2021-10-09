package com.adasoft.tis.dto.proposal;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalResponseDTO extends BaseResponseDTO<Long> {
    private long createdById;
    private String part;
    private String fileUrl;
    private long reviewId;
    private long adviserId;
}
