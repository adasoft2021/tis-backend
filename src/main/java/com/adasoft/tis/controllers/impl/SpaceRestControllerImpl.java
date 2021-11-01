package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.SpaceRestController;
import com.adasoft.tis.dto.spaceAnswer.CreateSpaceAnswerDTO;
import com.adasoft.tis.dto.spaceAnswer.SpaceAnswerResponseDTO;
import com.adasoft.tis.services.SpaceAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/spaces")
@AllArgsConstructor
public class SpaceRestControllerImpl implements SpaceRestController {
    private SpaceAnswerService spaceAnswerService;

    @PostMapping("/{spaceId}")
    @Override
    public ResponseEntity<SpaceAnswerResponseDTO> createSpaceAnswer(
        @PathVariable("spaceId") @NotNull Long spaceId,
        @Valid @RequestBody CreateSpaceAnswerDTO spaceAnswerDTO) {
        SpaceAnswerResponseDTO responseDTO = spaceAnswerService.create(spaceId, spaceAnswerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}