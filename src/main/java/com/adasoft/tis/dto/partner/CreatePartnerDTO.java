package com.adasoft.tis.dto.partner;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CreatePartnerDTO", description = "DTO para crear Partner")
public class CreatePartnerDTO extends com.adasoft.tis.core.dto.BaseCreateDTO {
    @Schema(
        name = "name",
        description = "nombre de un socio de GE",
        type = "String"
    )
    private String name;
    @Schema(
        name = "email",
        description = "email de un socio de GE",
        type = "String"
    )
    private String email;
    @Schema(
        name = "companyId",
        description = "ID de la company a adicionar socio",
        type = "Number"
    )
    Long companyId;
}
