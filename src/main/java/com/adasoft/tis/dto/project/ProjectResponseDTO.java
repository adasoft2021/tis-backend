package com.adasoft.tis.dto.project;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import com.adasoft.tis.domain.Publication;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectResponseDTO extends BaseResponseDTO<Long> {
    private String title;
    private Publication.PublicationType announcement;
    private Publication specificationSheet;
}