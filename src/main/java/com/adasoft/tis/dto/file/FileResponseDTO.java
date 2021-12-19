package com.adasoft.tis.dto.file;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import com.adasoft.tis.dto.observation.ObservationResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "FileEntityResponseDTO", description = "DTO para devolver FileEntity")
public class FileResponseDTO extends BaseResponseDTO<Long> {
    String name;
    String url;
    List<ObservationResponseDTO> observations;
}
