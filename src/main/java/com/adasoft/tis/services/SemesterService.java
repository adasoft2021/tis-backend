package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.DefaultTisDomainException;
import com.adasoft.tis.domain.Semester;
import com.adasoft.tis.dto.semester.SemesterResponseDTO;
import com.adasoft.tis.repository.SemesterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SemesterService {
    private ModelMapper semesterMapper;
    private SemesterRepository semesterRepository;

    public SemesterResponseDTO getNow() {
        Semester s = semesterRepository.getNow()
            .orElseThrow(() -> new DefaultTisDomainException(HttpStatus.NOT_FOUND,
                "No existe informacion del semestre"));
        return semesterMapper.map(s, SemesterResponseDTO.class);
    }
}
