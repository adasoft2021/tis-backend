package com.adasoft.tis.dto.company;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(name = "UpdateCompanyDTO", description = "DTO para la actualización de una propuesta")
@Getter
@Setter
public class UpdateCompanyDTO extends BaseUpdateDTO<Long> {
    @Schema(
        name = "shotName",
        description = "nombre corto de una GE",
        type = "String"
    )
    @NotNull
    private String shortName;
    @Schema(
        name = "name",
        description = "nombre de una GE",
        type = "String"
    )
    @NotNull
    private String name;
    @Schema(
        name = "companyType",
        description = "tipo de organizacion de una GE",
        type = "String"
    )
    @NotNull
    private String companyType;
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
    @NotNull
    private String email;
    @ArraySchema(
        schema = @Schema(
            type = "String"
        )
    )
    @NotNull
    private List<String> partners;
}
