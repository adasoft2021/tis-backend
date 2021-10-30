package com.adasoft.tis.dto.classCode;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassCodeResponseDTO extends BaseResponseDTO<Long> {
    String code;
}
