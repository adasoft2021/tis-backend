package com.adasoft.tis.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "FileEntityResponseDTO", description = "DTO para devolver FileEntity")
public class FileEntityResponseDTO extends com.adasoft.tis.core.dto.BaseResponseDTO {
    String name;
    String url;
}
