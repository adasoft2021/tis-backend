package com.adasoft.tis.dto.spaceAnswer;

import com.adasoft.tis.dto.semester.SemesterResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "SemesterSpaceAnswersResponseDTO", description = "DTO para devolver respuestas de un semestre")
public class SemesterSpaceAnswersResponseDTO extends SemesterResponseDTO {
    List<CompanySpaceAnswersResponseDTO> companies;
}
