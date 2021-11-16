package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Project;
import com.adasoft.tis.dto.project.CreateProjectDTO;
import com.adasoft.tis.dto.project.ProjectResponseDTO;
import com.adasoft.tis.repository.AdviserRepository;
import com.adasoft.tis.repository.ProjectRepository
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@AllArgsConstructor
@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    private AdviserRepository adviserRepository;
    private ModelMapper projectMapper;

    public ProjectResponseDTO  create(final Long adviserId, final CreateProjectDTO projectDTO) {
        checkArgument(adviserId != null, "El id de Adviser no puede ser nulo.");
        checkArgument(projectDTO != null, "El ProjectDTO a crear no puede ser nulo.");

        adviserRepository.findById(adviserId)
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        Project defaultProposal = projectMapper.map(projectDTO, Project.class);

        Project persistedProposal = projectRepository.save(defaultProposal);

        return projectMapper.map(persistedProposal, ProjectResponseDTO.class);
    }

    public Collection<ProjectResponseDTO> getAllByAdviserId(final Long adviserId) {
        checkArgument(adviserId != null, "El ID del adviser no puede ser nulo.");
        Collection<ProjectResponseDTO> projects = projectRepository.getAllByAdviserId(adviserId)
            .stream().filter(project -> !project.isDeleted())
            .map(project -> projectMapper.map(project, ProjectResponseDTO.class))
            .collect(Collectors.toSet());
        return new HashSet<>(projects);
    }
}
