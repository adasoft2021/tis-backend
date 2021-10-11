package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.dto.adviser.AdviserResponseDTO;
import com.adasoft.tis.dto.adviser.CreateAdviserDTO;
import com.adasoft.tis.dto.adviser.UpdateAdviserDTO;
import com.adasoft.tis.repository.AdviserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@AllArgsConstructor
@Service
public class AdviserService {
    private AdviserRepository adviserRepository;
    private ModelMapper adviserMapper;

    public AdviserResponseDTO create(final CreateAdviserDTO adviserDTO) {
        checkArgument(adviserDTO != null, "El AdviserDTO a crear no puede ser nulo.");

        Adviser defaultAdviser = adviserMapper.map(adviserDTO, Adviser.class);

        Adviser persistedAdviser = adviserRepository.save(defaultAdviser);

        return adviserMapper.map(persistedAdviser, AdviserResponseDTO.class);
    }

    public AdviserResponseDTO update(final Long adviserId, final UpdateAdviserDTO adviserDTO) {
        checkArgument(adviserId != null, "El id de Adviser a actualizar no puede ser nulo.");
        checkArgument(adviserDTO != null, "El AdviserDTO a actualizar no puede ser nulo.");

        Adviser foundAdviser = adviserRepository.findById(adviserId)
                .orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        if (foundAdviser.isDeleted()) {
            throw new EntityNotFoundException(Adviser.class, adviserId);
        }

        adviserMapper.map(adviserDTO, foundAdviser);
        adviserRepository.update(foundAdviser);

        return adviserMapper.map(foundAdviser, AdviserResponseDTO.class);
    }

    public AdviserResponseDTO delete(final Long adviserId) {
        checkArgument(adviserId != null, "El id de Adviser a actualizar no puede ser nulo.");

        Adviser foundAdviser = adviserRepository.findById(adviserId)
                .orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        if (foundAdviser.isDeleted()) {
            throw new EntityNotFoundException(Adviser.class, adviserId);
        }
        adviserRepository.deleteById(adviserId);
        foundAdviser.setDeleted(true);
        return adviserMapper.map(foundAdviser, AdviserResponseDTO.class);
    }

    public AdviserResponseDTO getById(final Long adviserId) {
        checkArgument(adviserId != null, "El id de Adviser a obtener no puede ser nulo.");

        Adviser foundAdviser = adviserRepository.findById(adviserId)
                .orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        if (foundAdviser.isDeleted()) {
            throw new EntityNotFoundException(Adviser.class, adviserId);
        }

        return adviserMapper.map(foundAdviser, AdviserResponseDTO.class);
    }

    public Collection<AdviserResponseDTO> getAll() {
        Collection<AdviserResponseDTO> advisers = adviserRepository.getAll()
                .stream().filter(adviser -> !adviser.isDeleted())
                .map(adviser -> adviserMapper.map(adviser, AdviserResponseDTO.class))
                .collect(Collectors.toSet());
        return new HashSet<>(advisers);
    }
}
