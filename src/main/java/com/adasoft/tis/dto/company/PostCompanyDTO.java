package com.adasoft.tis.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(name = "PostCompanyDTO", description = "DTO para registro de una GE")
@Getter
@Setter
public class PostCompanyDTO {
    @Schema(
        name = "name",
        description = "nombre de una GE",
        type = "String"
    )
    @NotNull
    private String name;
    @Schema(
        name = "name",
        description = "nombre de usuario de una GE",
        type = "String"
    )
    @NotNull
    private String username;
    @Schema(
        name = "shotName",
        description = "nombre corto de una GE",
        type = "String"
    )
    @NotNull
    private String shortName;
}
