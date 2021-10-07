package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.dto.proposal.CreateProposalDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "ProposalRestController", description = "Controlador para gestionar las Propuestas")
public interface ProposalRestController {

    ResponseEntity<ProposalResponseDTO> create(
        @Parameter(description = "ID del review a actualizar", example = "1")
            Long reviewId,
            CreateProposalDTO proposal
    );
    @Operation(summary = "Obtener de una propuesta por su ID", responses = {
        @ApiResponse(
            description = "Proposal devuelto exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ProposalResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al buscar el Proposal",
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
    ResponseEntity<ProposalResponseDTO> get(
        @Parameter(description = "ID del Proposal a obtener", example = "1")
            Long id
    );
}
