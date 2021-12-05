package com.adasoft.tis.dto.partner;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "UpdatePartnerDTO", description = "DTO para actualizar Partner")
public class UpdatePartnerDTO extends BaseUpdateDTO<Long> {
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
}
