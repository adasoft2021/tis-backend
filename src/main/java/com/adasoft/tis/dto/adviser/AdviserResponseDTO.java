package com.adasoft.tis.dto.adviser;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdviserResponseDTO extends BaseResponseDTO<Long> {
    private Long id;
    private String fisrtName;
    private String lastName;
    private String email;
}
