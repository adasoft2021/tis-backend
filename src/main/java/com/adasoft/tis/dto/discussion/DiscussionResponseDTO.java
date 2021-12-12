package com.adasoft.tis.dto.discussion;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscussionResponseDTO extends BaseResponseDTO<Long> {
    private String topic;
    private Long createdById;
}
