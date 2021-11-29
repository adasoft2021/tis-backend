package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.DefaultTisDomainException;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Publication;
import com.adasoft.tis.dto.publication.CreatePublicationDTO;
import com.adasoft.tis.dto.publication.PublicationResponseDTO;
import com.adasoft.tis.dto.publication.UpdatePublicationDTO;
import com.adasoft.tis.repository.AdviserRepository;
import com.adasoft.tis.repository.PublicationRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;
import static com.adasoft.tis.core.utils.Preconditions.checkUserId;

@AllArgsConstructor
@Service
public class PublicationService {
    private PublicationRepository publicationRepository;
    private AdviserRepository adviserRepository;
    private ModelMapper publicationMapper;

    public PublicationResponseDTO create(final CreatePublicationDTO publicationDTO) {
        checkArgument(publicationDTO != null, "El PublicationDTO no puede ser nulo.");

        Adviser foundAdviser = adviserRepository.findById(publicationDTO.getCreatedById())
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, publicationDTO.getCreatedById()));

        Publication defaultPublication = publicationMapper.map(publicationDTO, Publication.class);
        defaultPublication.setCreatedBy(foundAdviser);

        Publication persistedPublication = publicationRepository.save(defaultPublication);

        return publicationMapper.map(persistedPublication, PublicationResponseDTO.class);
    }

    public PublicationResponseDTO update(
        final Long userId,
        final Long publicationId,
        final UpdatePublicationDTO publicationDTO) {
        checkArgument(publicationId != null, "El id de Publication no puede ser nulo.");
        checkArgument(publicationDTO != null, "El PublicationDTO no puede ser nulo.");

        Publication foundPublication = publicationRepository.findById(publicationId)
            .orElseThrow(() -> new EntityNotFoundException(Publication.class, publicationId));

        checkUserId(userId, foundPublication.getCreatedBy().getId());

        publicationMapper.map(publicationDTO, foundPublication);

        foundPublication = publicationRepository.update(foundPublication);
        return publicationMapper.map(foundPublication, PublicationResponseDTO.class);
    }

    public Object delete(final Long userId, final Long publicationId) {
        checkArgument(publicationId != null, "El id de Publication no puede ser nulo.");

        Publication foundPublication = publicationRepository.findById(publicationId)
            .orElseThrow(() -> new EntityNotFoundException(Publication.class, publicationId));

        checkUserId(userId, foundPublication.getCreatedBy().getId());

        if (foundPublication.isDeleted()) {
            throw new DefaultTisDomainException(HttpStatus.NOT_ACCEPTABLE, "La Publicación no puede ser eliminada.");
        }

        foundPublication.setDeleted(true);
        publicationRepository.update(foundPublication);

        return null;
    }

    public Collection<PublicationResponseDTO> getByAdviserId(
        final Long adviserId,
        final Publication.PublicationType type) {
        checkArgument(adviserId != null, "El id de Adviser no puede ser nulo.");

        adviserRepository.findById(adviserId)
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        Collection<Publication> publications = publicationRepository.getByAdviserId(adviserId, type);

        return publications.stream()
            .map(publication -> publicationMapper.map(publication, PublicationResponseDTO.class))
            .collect(Collectors.toSet());
    }

    public Collection<PublicationResponseDTO> getByAdviserIdSemester(
        final Long adviserId,
        final Publication.PublicationType type,
        final String semester) {
        checkArgument(adviserId != null, "El id de Adviser no puede ser nulo.");
        checkArgument(semester != null, "El semestre a obtener no puede ser nulo.");

        adviserRepository.findById(adviserId)
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        Collection<Publication> publications = publicationRepository.getByAdviserIdSemester(adviserId, type, semester);

        return publications.stream()
            .map(publication -> publicationMapper.map(publication, PublicationResponseDTO.class))
            .collect(Collectors.toSet());
    }

    public Collection<PublicationResponseDTO> getHistoryByAdviserId(
        final Long adviserId,
        final Publication.PublicationType type) {
        checkArgument(adviserId != null, "El id de Adviser no puede ser nulo.");

        adviserRepository.findById(adviserId)
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        Collection<Publication> publications = publicationRepository.getByAdviserId(adviserId, type, true);

        return publications.stream()
            .map(publication -> publicationMapper.map(publication, PublicationResponseDTO.class))
            .collect(Collectors.toSet());
    }
}
