package com.adasoft.tis.dto.review;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDTO extends BaseResponseDTO<Long> {
    private int score;
    private String comment;
    private long createdById;
}
