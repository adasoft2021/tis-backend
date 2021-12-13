package com.adasoft.tis.dto.advertisement;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "AdvertisementResponseDTO", description = "DTO para devolver anuncio")
public class AdvertisementResponseDTO extends BaseResponseDTO<Long> {
    private String title;
    private String description;
    private Long createdById;
}
