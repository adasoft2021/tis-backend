package com.adasoft.tis.dto.user;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO extends BaseResponseDTO<Long> {

    private String name;
    private String token;
}
