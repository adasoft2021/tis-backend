package com.adasoft.tis.dto.qualification;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QualificationResponseDTO extends BaseResponseDTO<Long> {
    private String description;
    private Integer score;
    private int maxScore;
}
