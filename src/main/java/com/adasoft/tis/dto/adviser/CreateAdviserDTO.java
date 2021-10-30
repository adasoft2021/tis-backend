package com.adasoft.tis.dto.adviser;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(name = "CreateAdviserDTO", description = "DTO para creación de Adviser")
@Getter
@Setter
public class CreateAdviserDTO extends BaseCreateDTO {
    @Schema(
        name = "firstName",
        description = "nombre de un asesor",
        type = "String"
    )
    @NotNull
    String firstName;
    @Schema(
        name = "lastName",
        description = "apellido de un asesor",
        type = "String"
    )
    @NotNull
    String lastName;
    @Schema(
        name = "email",
        description = "email de un asesor",
        type = "String"
    )
    @NotNull
    String email;
}
