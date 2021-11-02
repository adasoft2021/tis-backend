package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.ClassCodeRestController;
import com.adasoft.tis.dto.classCode.CreateClassCodeDTO;
import com.adasoft.tis.services.ClassCodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/class-codes")
@AllArgsConstructor
public class ClassCodeRestControllerImpl implements ClassCodeRestController {
    private ClassCodeService classCodeService;

    @PostMapping
    @Override
    public ResponseEntity<?> validateClassCode(@Valid @RequestBody final CreateClassCodeDTO classCodeDTO) {
        classCodeService.validateClassCode(classCodeDTO);

        return ResponseEntity.noContent().build();
    }
}
