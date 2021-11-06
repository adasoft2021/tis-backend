package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.ObservationRestController;
import com.adasoft.tis.dto.observation.CreateObservationDTO;
import com.adasoft.tis.dto.observation.ObservationResponseDTO;
import com.adasoft.tis.dto.observation.UpdateObservationDTO;
import com.adasoft.tis.services.ObservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;


@RestController
@RequestMapping("/observations")
@AllArgsConstructor
public class ObservationRestControllerImpl implements ObservationRestController {
    private ObservationService observationService;

    @PostMapping
    @Override
    public ResponseEntity<ObservationResponseDTO> create(
        @RequestAttribute("userId") final Long userId,
        @NotNull @RequestParam(name = "proposal") final Long proposalId,
        @Valid @RequestBody final CreateObservationDTO observationDTO) {
        ObservationResponseDTO responseDTO = observationService.create(observationDTO, proposalId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{observationId}")
    @Override
    public ResponseEntity<ObservationResponseDTO> get(
        @RequestAttribute("userId") final Long userId,
        @NotNull @PathVariable("observationId") final Long id) {
        ObservationResponseDTO responseDTO = observationService.getById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{observationId}")
    @Override
    public ResponseEntity<ObservationResponseDTO> update(
        @RequestAttribute("userId") final Long userId,
        @NotNull @PathVariable("observationId") final Long id,
        @Valid @RequestBody final UpdateObservationDTO observationDTO) {
        ObservationResponseDTO responseDTO = observationService.update(id, observationDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{observationId}")
    @Override
    public ResponseEntity<ObservationResponseDTO> delete(
        @RequestAttribute("userId") final Long userId,
        @NotNull @PathVariable("observationId") final Long id) {
        ObservationResponseDTO responseDTO = observationService.delete(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    @Override
    public ResponseEntity<Collection<ObservationResponseDTO>> getAllByProposalId(
        @RequestAttribute("userId") final Long userId,
        @NotNull @RequestParam(name = "proposal") final Long proposalId) {
        Collection<ObservationResponseDTO> responses = observationService.getAllByProposalId(proposalId);
        return ResponseEntity.ok(responses);
    }
}
