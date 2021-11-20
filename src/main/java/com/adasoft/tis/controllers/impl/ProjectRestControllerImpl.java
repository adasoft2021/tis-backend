package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.ProjectRestController;
import com.adasoft.tis.dto.project.CreateProjectDTO;
import com.adasoft.tis.dto.project.ProjectResponseDTO;
import com.adasoft.tis.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.adasoft.tis.core.utils.Preconditions.checkUserId;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectRestControllerImpl implements ProjectRestController {
    private ProjectService projectService;
    @PostMapping
    @Override
    public ResponseEntity<ProjectResponseDTO> create(
        @RequestAttribute("userId") final Long userId,
        @NotNull @PathVariable("adviserId") final Long id,
        @Valid @RequestBody final CreateProjectDTO projectDTO) {
        checkUserId(userId, id);
        ProjectResponseDTO responseDTO = projectService.create(userId, projectDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
