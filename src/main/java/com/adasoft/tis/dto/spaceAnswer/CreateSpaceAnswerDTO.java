package com.adasoft.tis.dto.spaceAnswer;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import com.adasoft.tis.domain.FileEntity;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "CreateSpaceAnswerDTO", description = "DTO para crear SpaceAnswer) ")
public class CreateSpaceAnswerDTO extends BaseCreateDTO {
    @Schema(
        name = "spaceId",
        description = "ID de un Space",
        type = "Number"
    )
    Long spaceId;
    @Schema(
        name = "createdById",
        description = "ID de Company",
        type = "number"
    )
    Long createdById;
    @ArraySchema(
        schema = @Schema(implementation = FileEntity.class)
    )
    private List<FileEntity> files;
}