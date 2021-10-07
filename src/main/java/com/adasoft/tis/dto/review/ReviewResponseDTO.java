package com.adasoft.tis.dto.review;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import com.adasoft.tis.dto.qualification.QualificationResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ReviewResponseDTO extends BaseResponseDTO<Long> {
    private Integer totalScore;
    private String comment;
    private long createdById;
    private Set<QualificationResponseDTO> qualifications;
}
