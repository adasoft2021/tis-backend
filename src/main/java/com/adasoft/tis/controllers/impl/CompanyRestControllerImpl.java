package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.CompanyRestController;
import com.adasoft.tis.dto.company.CompanyResponseDTO;
import com.adasoft.tis.dto.company.CreateCompanyDTO;
import com.adasoft.tis.dto.company.UpdateCompanyDTO;
import com.adasoft.tis.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;


@RestController
@RequestMapping("/companies")
@AllArgsConstructor
public class CompanyRestControllerImpl implements CompanyRestController {
    private CompanyService companyService;

    @PostMapping
    @Override
    public ResponseEntity<CompanyResponseDTO> create(
        @Valid @RequestBody final CreateCompanyDTO companyDTO) {
        CompanyResponseDTO responseDTO = companyService.create(companyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{companyId}")
    @Override
    public ResponseEntity<CompanyResponseDTO> get(
        @NotNull @PathVariable("companyId") final Long id) {
        CompanyResponseDTO responseDTO = companyService.getById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{companyId}")
    @Override
    public ResponseEntity<CompanyResponseDTO> update(
        @NotNull @PathVariable("companyID") final Long id,
        @Valid @RequestBody final UpdateCompanyDTO companyDTO) {
        CompanyResponseDTO responseDTO = companyService.update(id, companyDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{companyId}")
    @Override
    public ResponseEntity<CompanyResponseDTO> delete(
        @NotNull @PathVariable("companyID") final Long id) {
        CompanyResponseDTO responseDTO = companyService.delete(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    @Override
    public ResponseEntity<Collection<CompanyResponseDTO>> getAll() {
        Collection<CompanyResponseDTO> responses = companyService.getAll();
        return ResponseEntity.ok(responses);
    }
}
