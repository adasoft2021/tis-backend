package com.adasoft.tis.dto.space;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpaceCompactResponseDTO extends BaseResponseDTO<Long> {
    private String title;
    private Long projectId;
}