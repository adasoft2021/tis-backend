package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.dto.adviser.AdviserResponseDTO;
import com.adasoft.tis.dto.adviser.CreateAdviserDTO;
import com.adasoft.tis.dto.adviser.UpdateAdviserDTO;
import com.adasoft.tis.dto.space.CreateSpaceDTO;
import com.adasoft.tis.repository.AdviserRepository;
import com.adasoft.tis.repository.SpaceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@AllArgsConstructor
@Service
public class SpaceService {
    private SpaceRepository spaceRepository;
    private ModelMapper spaceMapper;

    public AdviserResponseDTO create(final CreateSpaceDTO spaceDTO) {
        checkArgument(spaceDTO != null, "El SpaceDTO a crear no puede ser nulo.");

        Space defaultSpace = spaceMapper.map(spaceDTO, Space.class);

        Space persistedAdviser = spaceRepository.save(defaultSpace);

        return spaceMapper.map(persistedAdviser, AdviserResponseDTO.class);
    }
}
