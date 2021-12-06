package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.SemesterRestController;
import com.adasoft.tis.dto.semester.SemesterResponseDTO;
import com.adasoft.tis.services.SemesterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/semesters")
@AllArgsConstructor
public class SemesterRestControllerImpl implements SemesterRestController {
    private SemesterService semesterService;

    @GetMapping("/now")
    @Override
    public ResponseEntity<SemesterResponseDTO> getNow() {
        SemesterResponseDTO responseDTO = semesterService.getNow();

        return ResponseEntity.ok(responseDTO);
    }
}
