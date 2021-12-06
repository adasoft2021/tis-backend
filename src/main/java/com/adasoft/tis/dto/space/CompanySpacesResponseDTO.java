package com.adasoft.tis.dto.space;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Schema(name = "CompanySpacesResponseDTO", description = "DTO para devolver los espacios y respuestas de una GE")
public class CompanySpacesResponseDTO extends BaseResponseDTO<Long> {
    String companyName;
    Collection<CompanySpaceAndAnswersResponseDTO> answered;
    Collection<CompanySpaceAndAnswersResponseDTO> unanswered;
}
