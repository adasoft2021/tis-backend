package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Company;

import com.adasoft.tis.domain.Project;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.dto.space.CreateSpaceDTO;
import com.adasoft.tis.dto.space.SpaceCompactResponseDTO;
import com.adasoft.tis.dto.space.SpaceResponseDTO;
import com.adasoft.tis.repository.AdviserRepository;
import com.adasoft.tis.repository.CompanyRepository;
import com.adasoft.tis.repository.ProjectRepository;
import com.adasoft.tis.repository.SpaceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;
import static com.adasoft.tis.core.utils.Preconditions.checkUserId;

@AllArgsConstructor
@Service

public class SpaceService {
    private SpaceRepository spaceRepository;
    private ModelMapper spaceMapper;
    private AdviserRepository adviserRepository;
    private ProjectRepository projectRepository;
    private CompanyRepository companyRepository;

    public SpaceResponseDTO create(final CreateSpaceDTO spaceDTO, final Long adviserId) {
        checkArgument(spaceDTO != null, "El SpaceDTO a crear no puede ser nulo.");

        Adviser x = adviserRepository.findById(adviserId).orElseThrow(() ->
                new EntityNotFoundException(Adviser.class, adviserId)
        );

        Project y = projectRepository.findById(spaceDTO.getProyectId()).orElseThrow(() ->
                new EntityNotFoundException(Project.class, spaceDTO.getProyectId())
        );


        Space defaultSpace = spaceMapper.map(spaceDTO, Space.class);

        defaultSpace.setCreatedBy(x);

        defaultSpace.setProject(y);

        Space persistedSpace = spaceRepository.save(defaultSpace);

        return spaceMapper.map(persistedSpace, SpaceResponseDTO.class);

    }

    public Collection<SpaceCompactResponseDTO> getAdviserSpaces(Long adviserId, Space.SpaceType spaceType) {
        adviserRepository.findById(adviserId).orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        Collection<Space> spaces = spaceRepository.findByAdviser(adviserId, spaceType);
        return spaces.stream().map(space -> spaceMapper.map(space, SpaceCompactResponseDTO.class))
            .collect(Collectors.toSet());
    }

    public Collection<SpaceCompactResponseDTO> getCompanySpaces(Long companyId, Space.SpaceType spaceType) {
        Company foundCompany = companyRepository.findById(companyId)
            .orElseThrow(() -> new EntityNotFoundException(Company.class, companyId));
        Collection<Space> spaces = spaceRepository.findByAdviser(foundCompany.getAdviser().getId(), spaceType);
        if (foundCompany.getProject() != null)
            spaces = spaces.stream().filter(space ->
                    space.getProject().getId().equals(foundCompany.getProject().getId()))
                .collect(Collectors.toSet());
        return spaces.stream().map(space -> spaceMapper.map(space, SpaceCompactResponseDTO.class))
            .collect(Collectors.toSet());
    }

    public SpaceResponseDTO getSpace(Long spaceId) {
        Space space = spaceRepository.findById(spaceId)
            .orElseThrow(() -> new EntityNotFoundException(Space.class, spaceId));
        return spaceMapper.map(space, SpaceResponseDTO.class);
    }
}
