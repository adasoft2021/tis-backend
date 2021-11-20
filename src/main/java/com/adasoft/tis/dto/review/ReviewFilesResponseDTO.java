package com.adasoft.tis.dto.review;

import com.adasoft.tis.domain.SpaceAnswer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Schema(name = "ReviewFilesResponseDTO", description = "DTO para devolver una Review con spaceAnswers")
public class ReviewFilesResponseDTO extends ReviewResponseDTO {
    private Collection<SpaceAnswer> spaceAnswers;
}
