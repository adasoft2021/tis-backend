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
    public ResponseEntity<ProposalResponseDTO> create(CreateProposalDTO proposalDTO) {
        ProposalResponseDTO responseDTO = proposalService.create(proposalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{proposalId}")
    @Override
    public ResponseEntity<ProposalResponseDTO> update(
        @NotNull final Long id,
        @Valid @RequestBody final UpdateProposalDTO proposalDTO) {
        ProposalResponseDTO responseDTO = proposalService.update(id, proposalDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
