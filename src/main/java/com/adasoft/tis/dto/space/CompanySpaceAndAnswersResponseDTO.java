package com.adasoft.tis.dto.space;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import com.adasoft.tis.dto.review.ReviewCompactResponseDTO;
import com.adasoft.tis.dto.spaceAnswer.SpaceAnswerResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Schema(name = "CompanySpaceAndAnswersResponseDTO", description = "DTO para agrupar los espacios y respuestas de GE")
public class CompanySpaceAndAnswersResponseDTO extends BaseResponseDTO<Long> {
    ReviewCompactResponseDTO becauseOf;
    Collection<SpaceResponseDTO> spaces;
    Collection<Collection<SpaceAnswerResponseDTO>> spaceAnswers;
}