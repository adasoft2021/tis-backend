package com.adasoft.tis.dto.partner;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "PartnerResponseDTO", description = "DTO para devolver socio de GE")
public class PartnerResponseDTO extends BaseResponseDTO<Long> {
    String name;
    String email;
    Long companyId;
}
