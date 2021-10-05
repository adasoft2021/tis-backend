package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.QualificationRestController;
import com.adasoft.tis.dto.qualification.CreateQualificationDTO;
import com.adasoft.tis.dto.qualification.QualificationResponseDTO;
import com.adasoft.tis.dto.qualification.UpdateQualificationDTO;
import com.adasoft.tis.services.QualificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/qualifications")
@AllArgsConstructor
public class QualificationRestControllerImpl implements QualificationRestController {
    private QualificationService qualificationService;

    @PostMapping
    @Override
    public ResponseEntity<QualificationResponseDTO> create(
        @NotNull @RequestParam("reviewId") final Long reviewId,
        @Valid @RequestBody final CreateQualificationDTO qualificationDTO) {
        QualificationResponseDTO responseDTO = qualificationService.create(reviewId, qualificationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{qualificationId}")
    @Override
    public ResponseEntity<QualificationResponseDTO> update(
        @NotNull @PathVariable("qualificationId") final Long qualificationId,
        @Valid @RequestBody final UpdateQualificationDTO qualificationDTO) {
        QualificationResponseDTO responseDTO = qualificationService.update(qualificationId, qualificationDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
