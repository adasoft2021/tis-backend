package com.adasoft.tis.dto.comment;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CommentResponseDTO", description = "DTO para devolver comenterio")
public class CommentResponseDTO extends BaseResponseDTO<Long> {
    private String text;
    private Long createdById;
}
