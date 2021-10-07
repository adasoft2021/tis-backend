package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.dto.observation.CreateObservationDTO;
import com.adasoft.tis.dto.observation.ObservationResponseDTO;
import com.adasoft.tis.dto.observation.UpdateObservationDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "ObservationRestController", description = "Controlador para gestionar las Observaciones")
public interface ObservationRestController {
    @Operation(summary = "Creación de una revisión", responses = {
    @ApiResponse(
        description = "Observation creado exitosamente",
        responseCode = "201",
        content = @Content(
            mediaType = "application/json", schema = @Schema(implementation = ObservationResponseDTO.class)
        )
    ),
    @ApiResponse(
        description = "Fallo al crear el Observation",
        responseCode = "400",
        content = @Content(
            mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
        )
    )
    })
    ResponseEntity<ObservationResponseDTO> create(
        @Parameter(description = "ID de Proposal del cual se crea Observation", example = "1")
            Long proposalId,
        @RequestBody(description = "ObservationDTO que contiene los nuevos datos a crear")
            CreateObservationDTO observationDTO
    );
    @Operation(summary = "Obtener de una observacion por su ID", responses = {
        @ApiResponse(
            description = "Observation devuelto exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ProposalResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al buscar la Observation",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "No se encontró el ID de la Observation en el sistema",
            responseCode = "404",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<ObservationResponseDTO> get(
        @Parameter(description = "ID de la Observation a obtener", example = "1")
            Long id
    );

    @Operation(summary = "Actualización de una revisión por su ID", responses = {
        @ApiResponse(
            description = "Observation actualizado exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ObservationResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al actualizar el Observation",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "No se encontró el ID del Observation en el sistema",
            responseCode = "404",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<ObservationResponseDTO> update(
        @Parameter(description = "ID del Observation a actualizar", example = "1")
            Long id,
        @RequestBody(description = "ObservationDTO que contiene los nuevos datos a ser actualizados")
            UpdateObservationDTO observationDTO
    );
    @Operation(summary = "Eliminacion de una revisión por su ID", responses = {
        @ApiResponse(
            description = "Observation eliminado exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ObservationResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al eliminar el Observation",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "No se encontró el ID del Observation en el sistema",
            responseCode = "404",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<ObservationResponseDTO> delete(
        @Parameter(description = "ID del Observation a actualizar", example = "1")
            Long id
    );
}
