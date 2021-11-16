package com.adasoft.tis.dto.review;

import com.adasoft.tis.core.dto.BaseCreateDTO;
import com.adasoft.tis.domain.Observation;
import com.adasoft.tis.domain.Qualification;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Schema(name = "CreateReviewDTO", description = "DTO para la creación de una revisión")
@Getter
@Setter
public class CreateReviewDTO extends BaseCreateDTO {
    @JsonIgnore
    private String comment = null;
    @JsonIgnore
    private Set<Observation> observation = new HashSet<>();
    @JsonIgnore
    private Set<Qualification> qualification = new HashSet<>();
    @Schema(
        name = "createdById",
        description = "ID del Asesor que creó la revisión.",
        required = true,
        example = "1",
        type = "Number"
    )
    @NotNull(message = "Este campo no debe ser nulo")
    @Min(value = 1, message = "Este campo no puede ser menor a 1")
    private Long createdById;
    @Schema(
        name = "companyId",
        description = "ID de la GE en revisión.",
        required = true,
        example = "2",
        type = "Number"
    )
    @NotNull(message = "Este campo no debe ser nulo")
    @Min(value = 1, message = "Este campo no puede ser menor a 1")
    private Long companyId;
    @Schema(
        name = "title",
        description = "Título de la revisión.",
        required = true,
        example = "Orden de cambio",
        type = "String"
    )
    @NotNull
    private String title;
    @Schema(
        name = "spaces",
        description = "ID de spaces en revision",
        required = true,
        type = "Collection"
    )
    @NotNull(message = "Este campo no debe ser nulo")
    @NotEmpty(message = "Este campo no debe estar vacío")
    @Size(min = 1, message = "Debe proporcionar al menos un espacio")
    private Collection<Long> spaces;
}
