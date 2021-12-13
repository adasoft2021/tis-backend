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
    @Size(min = 15, max = 100, message = "Este campo debe tener entre 15 y 100 caracteres")
    private String topic;
    @NotNull
    @Min(value = 1, message = "Este campo no puede ser menor a 1")
    private Long createdById;
}
