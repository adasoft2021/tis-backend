package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Publication;
import com.adasoft.tis.dto.publication.PublicationResponseDTO;
import com.adasoft.tis.dto.publication.UpdatePublicationDTO;
import com.adasoft.tis.repository.AdviserRepository;
import com.adasoft.tis.repository.PublicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@Service
public class PublicationService {
    private PublicationRepository publicationRepository;
    private AdviserRepository adviserRepository;
    private ModelMapper publicationMapper;

    public PublicationResponseDTO update(final Long publicationId, final UpdatePublicationDTO publicationDTO) {
        checkArgument(publicationId != null, "El id de Publication a actualizar no puede ser nulo.");
        checkArgument(publicationDTO != null, "El PublicationDTO a actualizar no puede ser nulo.");

        Publication foundPublication = publicationRepository.findById(publicationId)
            .orElseThrow(() -> new EntityNotFoundException(Publication.class, publicationId));

        publicationMapper.map(publicationDTO, foundPublication);

        foundPublication = publicationRepository.update(foundPublication);
        return publicationMapper.map(foundPublication, PublicationResponseDTO.class);
    }

    public void delete(final Long publicationId) {
        checkArgument(publicationId != null, "El id de Publication a actualizar no puede ser nulo.");

        Publication foundPublication = publicationRepository.findById(publicationId)
            .orElseThrow(() -> new EntityNotFoundException(Publication.class, publicationId));

        foundPublication.setDeleted(true);
        publicationRepository.update(foundPublication);
    }

    public Collection<PublicationResponseDTO> getByAdviserId(
        final Long adviserId,
        final Publication.PublicationType type) {
        checkArgument(adviserId != null, "El id de Adviser a actualizar no puede ser nulo.");

        adviserRepository.findById(adviserId)
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        Collection<Publication> publications = publicationRepository.getByAdviserId(adviserId);

        return publications.stream()
            .map(publication -> publicationMapper.map(publication, PublicationResponseDTO.class))
            .collect(Collectors.toSet());
    }
}
