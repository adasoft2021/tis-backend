package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.dto.proposal.CreateProposalDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import com.adasoft.tis.dto.proposal.UpdateProposalDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "ProposalRestController", description = "Controlador para gestionar las Propuestas")
public interface ProposalRestController {
    @Operation(summary = "Creacion de una propuesta", responses = {
        @ApiResponse(
            description = "Propuesta creada exitosamente",
            responseCode = "201",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ProposalResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "FAllo al crear la Propuesta",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),

    })
    ResponseEntity<ProposalResponseDTO> create(
        @RequestBody(description = "ProposalDTO que contiene los nuevos datos a crear")
            CreateProposalDTO proposalDTO
    );

    @Operation(summary = "Actualización de una revisión por su ID", responses = {
        @ApiResponse(
            description = "Proposal actualizado exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ProposalResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al actualizar el Proposal",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "No se encontró el ID del Proposal en el sistema",
            responseCode = "404",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<ProposalResponseDTO> update(
        @Parameter(description = "ID del Proposal a actualizar", example = "1")
            Long id,
        @RequestBody(description = "ProposalDTO que contiene los nuevos datos a ser actualizados")
            UpdateProposalDTO reviewDTO
    );
}
