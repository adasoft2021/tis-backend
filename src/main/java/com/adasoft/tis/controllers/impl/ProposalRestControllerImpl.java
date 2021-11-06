package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.ProposalRestController;
import com.adasoft.tis.dto.proposal.CreateProposalDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import com.adasoft.tis.services.ProposalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@RestController
@RequestMapping("/proposals")
@AllArgsConstructor
public class ProposalRestControllerImpl implements ProposalRestController {
    private ProposalService proposalService;

    @PostMapping
    @Override
    public ResponseEntity<ProposalResponseDTO> create(
        @RequestAttribute("userId") final Long userId,
        @Valid @RequestBody final CreateProposalDTO proposalDTO) {
        ProposalResponseDTO responseDTO = proposalService.create(proposalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{proposalId}")
    @Override
    public ResponseEntity<ProposalResponseDTO> get(
        @RequestAttribute("userId") final Long userId,
        @NotNull @PathVariable("proposalId") final Long id) {
        ProposalResponseDTO responseDTO = proposalService.getById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    @Override
    public ResponseEntity<Collection<ProposalResponseDTO>> getAllByAdviserId(
        @RequestAttribute("userId") final Long userId,
        @NotNull @RequestParam(name = "adviser") final Long adviserId) {
        Collection<ProposalResponseDTO> responses = proposalService.getAllByAdviserId(adviserId);
        return ResponseEntity.ok(responses);
    }
}
