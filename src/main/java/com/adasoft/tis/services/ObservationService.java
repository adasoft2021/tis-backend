package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.File;
import com.adasoft.tis.domain.Observation;
import com.adasoft.tis.domain.Proposal;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.dto.observation.CreateObservationDTO;
import com.adasoft.tis.dto.observation.ObservationResponseDTO;
import com.adasoft.tis.dto.observation.UpdateObservationDTO;
import com.adasoft.tis.repository.FileRepository;
import com.adasoft.tis.repository.ObservationRepository;
import com.adasoft.tis.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;
import static com.adasoft.tis.core.utils.Preconditions.checkUserId;

@AllArgsConstructor
@Service
public class ObservationService {
    private ObservationRepository observationRepository;
    private ReviewRepository reviewRepository;
    private ModelMapper observationMapper;
    private FileRepository fileRepository;

    public ObservationResponseDTO create(
        final Long userId,
        final CreateObservationDTO observationDTO) {
        Long reviewId = observationDTO.getReviewId();
        Long fileId = observationDTO.getFileId();
        checkArgument(observationDTO != null, "El ObservationDTO a crear no puede ser nulo.");
        checkArgument(reviewId != null, "El ID del Review a observar no puede ser nulo.");
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new EntityNotFoundException(Review.class, reviewId));
        checkUserId(userId, review.getCreatedBy().getId());
        File file = fileRepository.findById(fileId)
            .orElseThrow(() -> new EntityNotFoundException(File.class, fileId));
        Observation defaultObservation = observationMapper.map(observationDTO, Observation.class);
        defaultObservation.setReview(review);
        Observation persistedObservation = observationRepository.save(defaultObservation);

        return observationMapper.map(persistedObservation, ObservationResponseDTO.class);
    }

    public ObservationResponseDTO update(
        final Long userId,
        final Long observationId,
        final UpdateObservationDTO observationDTO) {
        checkArgument(observationId != null, "El id de Observation a actualizar no puede ser nulo.");
        checkArgument(observationDTO != null, "El ObservationDTO a actualizar no puede ser nulo.");

        Observation foundObservation = observationRepository.findById(observationId)
            .orElseThrow(() -> new EntityNotFoundException(Observation.class, observationId));

        checkUserId(userId, foundObservation.getReview().getCreatedBy().getId());

        if (foundObservation.isDeleted()) {
            throw new EntityNotFoundException(Observation.class, observationId);
        }

        observationMapper.map(observationDTO, foundObservation);
        observationRepository.update(foundObservation);

        return observationMapper.map(foundObservation, ObservationResponseDTO.class);
    }

    public ObservationResponseDTO delete(final Long userId, final Long observationId) {
        checkArgument(observationId != null, "El id de Observation a actualizar no puede ser nulo.");

        Observation foundObservation = observationRepository.findById(observationId)
            .orElseThrow(() -> new EntityNotFoundException(Observation.class, observationId));

        checkUserId(userId, foundObservation.getReview().getCreatedBy().getId());

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

    public Collection<ObservationResponseDTO> getAllByReviewId(Long reviewId) {
        checkArgument(reviewId != null, "El ID del proposal no puede ser nulo.");
        reviewRepository.findById(reviewId)
            .orElseThrow(() -> new EntityNotFoundException(Proposal.class, reviewId));

        Collection<ObservationResponseDTO> observations = observationRepository.getAllByReviewId(reviewId)
            .stream().filter(observation -> !observation.isDeleted())
            .map(observation -> observationMapper.map(observation, ObservationResponseDTO.class))
            .collect(Collectors.toSet());
        return new HashSet<>(observations);
    }
}
