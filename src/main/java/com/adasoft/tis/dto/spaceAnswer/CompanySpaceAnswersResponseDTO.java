package com.adasoft.tis.dto.spaceAnswer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CompanySpaceAnswersResponseDTO", description = "DTO para agrupar todos los archivos enviados por la GE")
public class CompanySpaceAnswersResponseDTO extends SpaceAnswerResponseDTO {
    String project;
    String name = companyName;
}
