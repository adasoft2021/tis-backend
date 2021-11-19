package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.core.repository.AbstractTisRepository;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.domain.proyect.Project;
import com.adasoft.tis.dto.adviser.AdviserResponseDTO;
import com.adasoft.tis.dto.adviser.CreateAdviserDTO;
import com.adasoft.tis.dto.adviser.UpdateAdviserDTO;
import com.adasoft.tis.dto.space.CreateSpaceDTO;
import com.adasoft.tis.dto.space.SpaceResponseDTO;
import com.adasoft.tis.repository.AdviserRepository;
import com.adasoft.tis.repository.ProjectRepository;
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
    private AdviserRepository adviserRepository;
    private ProjectRepository projectRepository;

    public SpaceResponseDTO create(final CreateSpaceDTO spaceDTO, final Long adviserId) {
        checkArgument(spaceDTO != null, "El SpaceDTO a crear no puede ser nulo.");

        Adviser x = adviserRepository.findById(adviserId).orElseThrow(()->
            new EntityNotFoundException(Adviser.class,adviserId)
        );

        Project y = projectRepository.findById(spaceDTO.getProyectId()).orElseThrow(()->
                new EntityNotFoundException(Project.class,spaceDTO.getProyectId())
        );

        Space defaultSpace = spaceMapper.map(spaceDTO, Space.class);

        defaultSpace.setCreatedBy(x);

        defaultSpace.setProject(y);

        Space persistedSpace = spaceRepository.save(defaultSpace);

        return spaceMapper.map(persistedSpace, SpaceResponseDTO.class);
    }
}
