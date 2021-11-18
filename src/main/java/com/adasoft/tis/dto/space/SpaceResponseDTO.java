package com.adasoft.tis.dto.space;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SpaceResponseDTO extends BaseResponseDTO<Long> {
    private String title;
    private Long projectId;
    private LocalDateTime limitDate;
    private String description;
}
