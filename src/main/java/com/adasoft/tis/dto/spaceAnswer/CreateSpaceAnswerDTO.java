package com.adasoft.tis.dto.spaceAnswer;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import com.adasoft.tis.domain.File;
import com.adasoft.tis.dto.file.CreateFileDTO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull
    Long spaceId;
    @Schema(
        name = "createdById",
        description = "ID de Company",
        type = "number"
    )
    @NotNull
    Long createdById;
    @ArraySchema(
        schema = @Schema(implementation = File.class)
    )
    @Size(min = 1)
    private List<CreateFileDTO> files;
}