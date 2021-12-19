package com.adasoft.tis.dto.file;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema(name = "CreateFileDTO", description = "DTO para crer una referencia a archivo")
public class CreateFileDTO extends BaseCreateDTO {
    @Schema(
        name = "name",
        description = "nombre del archivo",
        type = "String"
    )
    @NotNull
    private String name;
    @Schema(
        name = "url",
        description = "ruta del archivo",
        type = "String"
    )
    @NotNull
    private String url;
}