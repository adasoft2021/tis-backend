package com.adasoft.tis.dto.company;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(name = "CreateCompanyDTO", description = "DTO para creación de una observation")
@Getter
@Setter
public class CreateCompanyDTO extends BaseCreateDTO {
    @Schema(
        name = "name",
        description = "nombre de una GE",
        type = "String"
    )
    @NotNull
    String name;
    @Schema(
        name = "userName",
        description = "nombre de usuario de una GE",
        type = "String"
    )
    @NotNull
    String userName;
    @Schema(
        name = "password",
        description = "password de una GE",
        type = "String"
    )
    @NotNull
    String password;

}
