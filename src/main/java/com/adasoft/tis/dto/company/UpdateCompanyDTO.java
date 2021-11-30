package com.adasoft.tis.dto.company;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(name = "UpdateCompanyDTO", description = "DTO para la actualización de una propuesta")
@Getter
@Setter
public class UpdateCompanyDTO extends BaseUpdateDTO<Long> {

    @Schema(
        name = "address",
        description = "direccion de una GE",
        type = "String"
    )
    @NotNull
    private String address;
    @Schema(
        name = "email",
        description = "correo electronico de una GE",
        type = "String"
    )
    private String email;
    @Schema(
        name = "telephone",
        description = "telefono de una GE",
        type = "String"
    )
    @NotNull
    private String telephone;

}
