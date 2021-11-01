package com.adasoft.tis.dto.adviser;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(name = "UpdateAdviserDTO", description = "DTO para la actualización de Adviser")
@Getter
@Setter
public class UpdateAdviserDTO extends BaseUpdateDTO<Long> {

    @Schema(
        name = "email",
        description = "email de un asesor",
        type = "String"
    )
    @NotNull
    String email;

}
