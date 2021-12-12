package com.adasoft.tis.dto.comment;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CreateCommentDTO", description = "DTO para crear comentario")
public class CreateCommentDTO extends BaseCreateDTO {
    private Long createdById;
    private String text;

}
