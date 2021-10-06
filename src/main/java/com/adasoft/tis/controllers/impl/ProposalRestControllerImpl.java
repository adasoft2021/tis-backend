package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.ProposalRestController;
import com.adasoft.tis.dto.proposal.CreateProposalDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import com.adasoft.tis.dto.proposal.UpdateProposalDTO;
import com.adasoft.tis.services.ProposalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/proposals")
@AllArgsConstructor
public class ProposalRestControllerImpl implements ProposalRestController {
    private ProposalService proposalService;

    @PostMapping
    @Override
    public ResponseEntity<ProposalResponseDTO> create(@NotNull @RequestParam("reviewId") Long reviewId,
                                                      @RequestBody @Valid CreateProposalDTO proposal) {
        ProposalResponseDTO responseDTO = proposalService.create(reviewId,proposal);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/{proposalId}")
    @Override
    public ResponseEntity<ProposalResponseDTO> get(
        @NotNull final Long id) {
        ProposalResponseDTO responseDTO = proposalService.getById(id);
        return ResponseEntity.ok(responseDTO);
    }
}
