package com.adasoft.tis.dto.comment;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(name = "CreateCommentDTO", description = "DTO para crear comentario")
public class CreateCommentDTO extends BaseCreateDTO {
    @NotNull
    @Min(value = 1, message = "Este campo no puede ser menor a 1")
    private Long createdById;
    @Schema(
        name = "text",
        description = "Texto del comentario.",
        required = true,
        type = "String"
    )
    @NotNull
    private String text;

}
