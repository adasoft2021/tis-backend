package com.adasoft.tis.dto.company;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(name = "UpdateProposalDTO", description = "DTO para la actualización de una propuesta")
@Getter
@Setter
public class UpdateCompanyDTO extends BaseUpdateDTO<Long> {
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