package com.adasoft.tis.dto.project;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import com.adasoft.tis.domain.Publication;
import com.adasoft.tis.dto.publication.PublicationResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectResponseDTO extends BaseResponseDTO<Long> {
    private String title;
    private PublicationResponseDTO announcement;
    private PublicationResponseDTO specificationSheet;
}