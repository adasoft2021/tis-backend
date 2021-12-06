package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.dto.project.CreateProjectDTO;
import com.adasoft.tis.dto.project.ProjectResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "ProjectRestController", description = "Controlador para gestionar los Projectos")
public interface ProjectRestController {
    @Operation(summary = "Creación de una Project", responses = {
        @ApiResponse(
            description = "Project creado exitosamente",
            responseCode = "201",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ProjectResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al crear el Project",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<ProjectResponseDTO> create(
        @Parameter(description = "ID del Adviser a obtener sus proyectos", example = "1")
            Long adviserId,
        @RequestBody(description = "ProjectDTO que contiene los nuevos datos a crear")
            CreateProjectDTO projectDTO
    );
}
