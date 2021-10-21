package com.adasoft.tis.services;

import com.adasoft.tis.dto.semester.SemesterResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SemesterService {
    public SemesterResponseDTO getNow() {
        SemesterResponseDTO responseDTO = new SemesterResponseDTO();
        responseDTO.setSemester("2-2021");
        return responseDTO;
    }
}
