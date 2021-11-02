package com.adasoft.tis.dto.classCode;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(name = "CreateClassCodeDTO", description = "DTO para validar un código de clase")
@Getter
@Setter
public class CreateClassCodeDTO extends BaseCreateDTO {
    @NotNull
    @Size(min = 11, max = 11, message = "Este campo debe tener 11 caractere")
    private String code;
}
