package com.adasoft.tis.dto.observation;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObservationResponseDTO extends BaseResponseDTO<Long> {
    private String title;
    private String description;
    private Long reviewId;
}
