package com.adasoft.tis.dto.discussion;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(name = "CreateDiscussionDTO", description = "DTO para crear discusiones")
public class CreateDiscussionDTO extends BaseCreateDTO {
    @Schema(
        name = "topic",
        description = "Topic de discusion a crear.",
        required = true,
        type = "String"
    )
    @NotNull
    @Size(min = 15, max = 40, message = "Este campo debe tener entre 15 y 40 caracteres")
    private String topic;
    @Schema(
        name = "createdById",
        description = "ID  del usuario.",
        required = true,
        type = "Number"
    )
    @NotNull
    @Min(value = 1, message = "Este campo no puede ser menor a 1")
    private Long createdById;
    @Schema(
        name = "companyId",
        description = "ID  de company cuando el asesor crea la discusion.",
        required = true,
        type = "Number"
    )
    @NotNull
    private Long companyId;
}
