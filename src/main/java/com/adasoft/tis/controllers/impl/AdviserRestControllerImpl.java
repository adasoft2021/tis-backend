package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.AdviserRestController;
import com.adasoft.tis.dto.adviser.AdviserResponseDTO;
import com.adasoft.tis.dto.adviser.CreateAdviserDTO;
import com.adasoft.tis.dto.adviser.UpdateAdviserDTO;
import com.adasoft.tis.services.AdviserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;


@RestController
@RequestMapping("/advisers")
@AllArgsConstructor
public class AdviserRestControllerImpl implements AdviserRestController {
    private AdviserService adviserService;

    @PostMapping
    @Override
    public ResponseEntity<AdviserResponseDTO> create(
        @Valid @RequestBody final CreateAdviserDTO adviserDTO) {
        AdviserResponseDTO responseDTO = adviserService.create(adviserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{adviserId}")
    @Override
    public ResponseEntity<AdviserResponseDTO> get(
        @NotNull @PathVariable("adviserId") final Long id) {
        AdviserResponseDTO responseDTO = adviserService.getById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{adviserId}")
    @Override
    public ResponseEntity<AdviserResponseDTO> update(
        @NotNull @PathVariable("adviserID") final Long id,
        @Valid @RequestBody final UpdateAdviserDTO adviserDTO) {
        AdviserResponseDTO responseDTO = adviserService.update(id, adviserDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{adviserId}")
    @Override
    public ResponseEntity<AdviserResponseDTO> delete(
        @NotNull @PathVariable("adviserID") final Long id) {
        AdviserResponseDTO responseDTO = adviserService.delete(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    @Override
    public ResponseEntity<Collection<AdviserResponseDTO>> getAll() {
        Collection<AdviserResponseDTO> responses = adviserService.getAll();
        return ResponseEntity.ok(responses);
    }
}
