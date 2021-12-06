package com.adasoft.tis.dto.space;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "SpaceCompactResponseDTO", description = "DTO para devolver lista de spaces")
public class SpaceCompactResponseDTO extends BaseResponseDTO<Long> {
    private String title;
    private Long projectId;
}