package com.adasoft.tis.dto.publication;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PublicationResponseDTO extends BaseResponseDTO<Long> {
    private String title;
    private LocalDateTime date;
    private String code;
    private String semester;
    private String fileUrl;
}
