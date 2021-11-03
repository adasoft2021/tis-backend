package com.adasoft.tis.dto.spaceAnswer;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import com.adasoft.tis.domain.FileEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "SpaceAnswerResponseDTO", description = "DTO para devolver SpaceAnswer")
public class SpaceAnswerResponseDTO extends BaseResponseDTO<Long> {
    Long spaceId;
    String companyName;
    List<FileEntity> files;
}
