package com.adasoft.tis.dto.review;

import com.adasoft.tis.core.dto.BaseResponseDTO;
import com.adasoft.tis.dto.observation.ObservationResponseDTO;
import com.adasoft.tis.dto.qualification.QualificationResponseDTO;
import com.adasoft.tis.dto.space.SpaceCompactResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ReviewResponseDTO extends BaseResponseDTO<Long> {
    private boolean published;
    private String status;
    private String comment;
    private String adviserName;
    private String companyName;
    private Set<SpaceCompactResponseDTO> spaces;
    private Set<QualificationResponseDTO> qualifications;
    private Set<ObservationResponseDTO> observations;
}
