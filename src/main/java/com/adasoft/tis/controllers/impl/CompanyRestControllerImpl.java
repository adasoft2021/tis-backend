package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.CompanyRestController;
import com.adasoft.tis.dto.company.CompanyResponseDTO;
import com.adasoft.tis.dto.company.CreateCompanyDTO;
import com.adasoft.tis.dto.company.UpdateCompanyDTO;
import com.adasoft.tis.dto.review.ReviewCompactResponseDTO;
import com.adasoft.tis.dto.user.UserResponseDTO;
import com.adasoft.tis.services.CompanyService;
import com.adasoft.tis.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

import static com.adasoft.tis.core.utils.Preconditions.checkUserId;


@RestController
@RequestMapping("/companies")
@AllArgsConstructor
public class CompanyRestControllerImpl implements CompanyRestController {
    private CompanyService companyService;
    private ReviewService reviewService;

    @PostMapping
    @Override
    public ResponseEntity<UserResponseDTO> create(
        @NotNull @RequestParam(name = "registrationCode") final String registrationCode,
        @Valid @RequestBody final CreateCompanyDTO companyDTO) {
        UserResponseDTO responseDTO = companyService.create(registrationCode, companyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{companyId}")
    @Override
    public ResponseEntity<CompanyResponseDTO> get(
        @RequestAttribute("userId") final Long userId,
        @NotNull @PathVariable("companyId") final Long id) {
        checkUserId(userId, id);
        CompanyResponseDTO responseDTO = companyService.getById(userId);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{companyId}")
    @Override
    public ResponseEntity<CompanyResponseDTO> update(
        @RequestAttribute("userId") final Long userId,
        @NotNull @PathVariable("companyId") final Long id,
        @Valid @RequestBody final UpdateCompanyDTO companyDTO) {
        checkUserId(userId, id);
        CompanyResponseDTO responseDTO = companyService.update(userId, companyDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{companyId}")
    @Override
    public ResponseEntity<CompanyResponseDTO> delete(
        @RequestAttribute("userId") final Long userId,
        @NotNull @PathVariable("companyId") final Long id) {
        checkUserId(userId, id);
        CompanyResponseDTO responseDTO = companyService.delete(userId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    @Override
    public ResponseEntity<Collection<CompanyResponseDTO>> getAll() {
        Collection<CompanyResponseDTO> responses = companyService.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{companyId}/reviews")
    @Override
    public ResponseEntity<Collection<ReviewCompactResponseDTO>> getCompanyReviews(
        @RequestAttribute("userId") final Long userId,
        @NotNull @PathVariable("companyId") final Long id) {
        checkUserId(userId, id);
        Collection<ReviewCompactResponseDTO> responses = reviewService.getCompanyReviews(id);
        return ResponseEntity.ok(responses);
    }
}
