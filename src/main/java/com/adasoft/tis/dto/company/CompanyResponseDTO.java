package com.adasoft.tis.dto.company;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponseDTO extends BaseResponseDTO<Long> {
    private Long id;
    private String userName;
    private String name;
}
