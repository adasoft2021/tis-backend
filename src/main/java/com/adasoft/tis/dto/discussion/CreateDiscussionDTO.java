package com.adasoft.tis.dto.discussion;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CreateDiscussionDTO", description = "DTO para crear discusiones")
public class CreateDiscussionDTO extends BaseCreateDTO {

    private Long companyId;
}
