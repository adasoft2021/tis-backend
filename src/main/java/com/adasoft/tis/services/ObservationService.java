package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Observation;
import com.adasoft.tis.domain.Proposal;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.dto.observation.CreateObservationDTO;
import com.adasoft.tis.dto.observation.ObservationResponseDTO;
import com.adasoft.tis.dto.observation.UpdateObservationDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import com.adasoft.tis.repository.ObservationRepository;
import com.adasoft.tis.repository.ProposalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@AllArgsConstructor
@Service
public class ObservationService {
    private ObservationRepository observationRepository;
    private ProposalRepository proposalRepository;
    private ModelMapper observationMapper;

    public ObservationResponseDTO create(final CreateObservationDTO observationDTO, final Long proposalId) {
        checkArgument(observationDTO != null, "El ObservationDTO a crear no puede ser nulo.");
        checkArgument(proposalId != null, "El ID del Proposal a observar no puede ser nulo.");
        Proposal proposal = proposalRepository.findById(proposalId)
            .orElseThrow(()-> new EntityNotFoundException(Review.class,proposalId));
        Observation defaultObservation = observationMapper.map(observationDTO, Observation.class);
        defaultObservation.setProposal(proposal);
        Observation persistedObservation = observationRepository.save(defaultObservation);

        return observationMapper.map(persistedObservation, ObservationResponseDTO.class);
    }

    public ObservationResponseDTO update(final Long observationId, final UpdateObservationDTO observationDTO) {
        checkArgument(observationId != null, "El id de Observation a actualizar no puede ser nulo.");
        checkArgument(observationDTO != null, "El ObservationDTO a actualizar no puede ser nulo.");

        Observation foundObservation = observationRepository.findById(observationId)
            .orElseThrow(() -> new EntityNotFoundException(Observation.class, observationId));

        if (foundObservation.isDeleted()) {
            throw new EntityNotFoundException(Observation.class, observationId);
        }

        observationMapper.map(observationDTO, foundObservation);
        observationRepository.update(foundObservation);

        return observationMapper.map(foundObservation, ObservationResponseDTO.class);
    }
    public ObservationResponseDTO delete(final Long observationId) {
        checkArgument(observationId != null, "El id de Observation a actualizar no puede ser nulo.");

        Observation foundObservation = observationRepository.findById(observationId)
            .orElseThrow(() -> new EntityNotFoundException(Observation.class, observationId));

        if (foundObservation.isDeleted()) {
            throw new EntityNotFoundException(Observation.class, observationId);
        }
        observationRepository.deleteById(observationId);
        foundObservation.setDeleted(true);
        return observationMapper.map(foundObservation, ObservationResponseDTO.class);
    }

    public ObservationResponseDTO getById(final Long observationId) {
        checkArgument(observationId != null, "El id de Observation a obtener no puede ser nulo.");

        Observation foundObservation = observationRepository.findById(observationId)
            .orElseThrow(() -> new EntityNotFoundException(Observation.class, observationId));

        if (foundObservation.isDeleted()) {
            throw new EntityNotFoundException(Observation.class, observationId);
        }

        return observationMapper.map(foundObservation, ObservationResponseDTO.class);
    }
}
