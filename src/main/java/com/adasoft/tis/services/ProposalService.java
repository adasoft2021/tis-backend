package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Proposal;
import com.adasoft.tis.dto.proposal.CreateProposalDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import com.adasoft.tis.repository.ProposalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@AllArgsConstructor
@Service
public class ProposalService {
    private ProposalRepository proposalRepository;
    private ModelMapper proposalMapper;

    public ProposalResponseDTO create(final CreateProposalDTO proposalDTO) {
        checkArgument(proposalDTO != null, "El ProposalDTO a crear no puede ser nulo.");

        Proposal defaultProposal = proposalMapper.map(proposalDTO, Proposal.class);

        Proposal persistedProposal = proposalRepository.save(defaultProposal);

        return proposalMapper.map(persistedProposal, ProposalResponseDTO.class);
    }

    public ProposalResponseDTO getById(final Long proposalId) {
        checkArgument(proposalId != null, "El id de Proposal a obtener no puede ser nulo.");

        Proposal foundProposal = proposalRepository.findById(proposalId)
            .orElseThrow(() -> new EntityNotFoundException(Proposal.class, proposalId));

        if (foundProposal.isDeleted()) {
            throw new EntityNotFoundException(Proposal.class, proposalId);
        }

        return proposalMapper.map(foundProposal, ProposalResponseDTO.class);
    }
}
