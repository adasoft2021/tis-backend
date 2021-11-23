package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.SpaceRestController;
import com.adasoft.tis.dto.space.SpaceResponseDTO;
import com.adasoft.tis.dto.spaceAnswer.CreateSpaceAnswerDTO;
import com.adasoft.tis.dto.spaceAnswer.SpaceAnswerResponseDTO;
import com.adasoft.tis.services.SpaceAnswerService;
import com.adasoft.tis.services.SpaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.adasoft.tis.core.utils.Preconditions.checkUserId;

@RestController
@RequestMapping("/spaces")
@AllArgsConstructor
public class SpaceRestControllerImpl implements SpaceRestController {
    private SpaceAnswerService spaceAnswerService;
    private SpaceService spaceService;

    @PostMapping("/{spaceId}")
    @Override
    public ResponseEntity<SpaceAnswerResponseDTO> createSpaceAnswer(
        @RequestAttribute("userId") final Long userId,
        @PathVariable("spaceId") @NotNull Long spaceId,
        @Valid @RequestBody CreateSpaceAnswerDTO spaceAnswerDTO) {
        checkUserId(userId, spaceAnswerDTO.getCreatedById());
        SpaceAnswerResponseDTO responseDTO = spaceAnswerService.create(spaceId, spaceAnswerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{spaceId}")
    @Override
    public ResponseEntity<SpaceResponseDTO> getSpace(
        @RequestAttribute("userId") Long userId,
        @NotNull @PathVariable Long spaceId) {
        SpaceResponseDTO response = spaceService.getSpace(spaceId);
        return ResponseEntity.ok(response);
    }
}