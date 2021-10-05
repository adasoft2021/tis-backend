package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.dto.qualification.CreateQualificationDTO;
import com.adasoft.tis.dto.qualification.QualificationResponseDTO;
import com.adasoft.tis.dto.qualification.UpdateQualificationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "QualificationRestController", description = "Controlador para gestionar las Calificaciones")
public interface QualificationRestController {
    @Operation(summary = "Creación de una calificación parcial", responses = {
        @ApiResponse(
            description = "Qualification creado exitosamente",
            responseCode = "201",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = QualificationResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al crear el Qualification",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<QualificationResponseDTO> create(
        @Parameter(description = "ID del Review al cual se creará una calificación", example = "1")
            Long reviewId,
        @RequestBody(description = "QualificationDTO que contiene los nuevos datos a crear")
            CreateQualificationDTO qualificationDTO
    );

    @Operation(summary = "Actualización de una calificación por su ID", responses = {
        @ApiResponse(
            description = "Qualification actualizado exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = QualificationResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al actualizar el Qualification",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "No se encontró el ID del Qualification en el sistema",
            responseCode = "404",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<QualificationResponseDTO> update(
        @Parameter(description = "ID del Qualification a actualizar", example = "1")
            Long id,
        @RequestBody(description = "QualificationDTO que contiene los nuevos datos a ser actualizados")
            UpdateQualificationDTO qualificationDTO);
}
