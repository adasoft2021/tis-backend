package com.adasoft.tis.dto.review;

import com.adasoft.tis.core.dto.BaseUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "UpdateReviewDTO", description = "DTO para la actualización de una revisión")
@Getter
@Setter
public class UpdateReviewDTO extends BaseUpdateDTO<Long> {
    @Schema(name = "score", description = "Nota de la revisión.", example = "100", type = "Number")
    private int score;
    @Schema(
        name = "comment",
        description = "Comentario de la revisión.",
        example = "Este es un nuevo comentario",
        type = "String"
    )
    private String comment;
}
