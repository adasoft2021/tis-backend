package com.adasoft.tis.dto.review;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import com.adasoft.tis.domain.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "ReviewCompactResponseDTO", description = "DTO para ")
public class ReviewCompactResponseDTO extends BaseResponseDTO<Long> {
    String companyName;
    Review.Status status;

}
