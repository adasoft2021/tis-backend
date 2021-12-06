package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Project;
import com.adasoft.tis.domain.Publication;
import com.adasoft.tis.dto.project.CreateProjectDTO;
import com.adasoft.tis.dto.project.ProjectResponseDTO;
import com.adasoft.tis.dto.publication.PublicationResponseDTO;
import com.adasoft.tis.repository.AdviserRepository;
import com.adasoft.tis.repository.ProjectRepository;
import com.adasoft.tis.repository.PublicationRepository;
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
    private PublicationService publicationService;
    private PublicationRepository publicationRepository;
    private ModelMapper projectMapper;

    public ProjectResponseDTO  create(final Long adviserId, final CreateProjectDTO projectDTO) {
        checkArgument(adviserId != null, "El id de Adviser no puede ser nulo.");
        checkArgument(projectDTO != null, "El ProjectDTO a crear no puede ser nulo.");

        Adviser adviser = adviserRepository.findById(adviserId)
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, adviserId));

        Publication announcement = publicationRepository.findById(projectDTO.getCreateannouncementId())
            .orElseThrow(() -> new EntityNotFoundException(Publication.class, projectDTO.getCreateannouncementId()));
        Publication specificationSheet = publicationRepository.findById(projectDTO.getSpecificationSheetId())
            .orElseThrow(() -> new EntityNotFoundException(Publication.class, projectDTO.getSpecificationSheetId()));

        Project defaultProject = projectMapper.map(projectDTO, Project.class);
        defaultProject.setCreatedBy(adviser);
        defaultProject.setAnnouncement(announcement);
        defaultProject.setSpecificationSheet(specificationSheet);
        Project persistedProject = projectRepository.save(defaultProject);

        PublicationResponseDTO announcementResponseDTO = publicationService.findByID(projectDTO.getCreateannouncementId());
        PublicationResponseDTO sheetResponseDTO = publicationService.findByID(projectDTO.getSpecificationSheetId());
        ProjectResponseDTO res = projectMapper.map(persistedProject, ProjectResponseDTO.class);
        res.setAnnouncement(announcementResponseDTO);
        res.setSpecificationSheet(sheetResponseDTO);
        return res;
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
