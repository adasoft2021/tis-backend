package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.dto.space.SpaceCompactResponseDTO;
import com.adasoft.tis.repository.AdviserRepository;
import com.adasoft.tis.repository.SpaceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SpaceService {
    private AdviserRepository adviserRepository;
    private SpaceRepository spaceRepository;
    private ModelMapper spaceMapper;

    public Collection<SpaceCompactResponseDTO> getAdviserSpaces(Long adviserId, Space.SpaceType spaceType) {
        adviserRepository.findById(adviserId).orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        Collection<Space> spaces = spaceRepository.findByAdviser(adviserId, spaceType);
        return spaces.stream().map(space -> spaceMapper.map(space, SpaceCompactResponseDTO.class))
            .collect(Collectors.toSet());
    }
}
