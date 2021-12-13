package com.adasoft.tis.dto.discussion;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
@Schema(name = "DiscussionResponseDTO", description = "DTO para devolver discusiones")
public class DiscussionResponseDTO extends BaseResponseDTO {
    private String topic;
    private Long createdById;
}
