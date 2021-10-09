package com.adasoft.tis.dto.adviser;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Schema(name = "CreateObservationDTO", description = "DTO para creación de una observation")
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
            name = "userName",
            description = "nombre de usuario de un asesor",
            type = "String"
    )
    @NotNull
    String userName;
    @Schema(
            name = "password",
            description = "password de un asesor",
            type = "String"
    )
    @NotNull
    String password;

}
