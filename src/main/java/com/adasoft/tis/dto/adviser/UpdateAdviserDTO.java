package com.adasoft.tis.dto.adviser;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(name = "UpdateProposalDTO", description = "DTO para la actualización de una propuesta")
@Getter
@Setter
public class UpdateAdviserDTO extends BaseUpdateDTO<Long> {
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
