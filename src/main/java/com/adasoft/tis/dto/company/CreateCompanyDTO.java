package com.adasoft.tis.dto.company;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import com.adasoft.tis.dto.partner.CreatePartnerDTO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Schema(name = "CreateCompanyDTO", description = "DTO para creación de una observation")
@Getter
@Setter
public class CreateCompanyDTO extends BaseCreateDTO {
    @Schema(
        name = "shortName",
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
    private String address;
    @Schema(
        name = "email",
        description = "correo electronico de una GE",
        type = "String"
    )
    @NotNull
    private String email;
    @Schema(
        name = "telephone",
        description = "telefono de una GE",
        type = "String"
    )
    private String telephone;
    @ArraySchema(
        schema = @Schema(
            type = "Collection"
        )
    )
    private Collection<CreatePartnerDTO> partners;
}
