package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.dto.proposal.CreateProposalDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@Tag(name = "ProposalRestController", description = "Controlador para gestionar las Propuestas")
public interface ProposalRestController {
    @Operation(summary = "Creación de una Proposal", responses = {
        @ApiResponse(
            description = "Proposal creado exitosamente",
            responseCode = "201",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ProposalResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al crear el Proposal",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "No autorizado, el token es inválido",
            responseCode = "401",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    }, parameters = @Parameter(
        in = ParameterIn.HEADER,
        name = "X-Token",
        description = "Token del usuario",
        schema = @Schema(implementation = String.class),
        required = true
    ))
    ResponseEntity<ProposalResponseDTO> create(
        Long userId,
        @RequestBody(description = "ProposalDTO que contiene los nuevos datos a crear")
            CreateProposalDTO proposalDTO
    );

    @Operation(summary = "Obtener una propuesta por su ID", responses = {
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
            description = "No autorizado, el token es inválido",
            responseCode = "401",
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
    }, parameters = @Parameter(
        in = ParameterIn.HEADER,
        name = "X-Token",
        description = "Token del usuario",
        schema = @Schema(implementation = String.class),
        required = true
    ))
    ResponseEntity<ProposalResponseDTO> get(
        Long userId,
        @Parameter(description = "ID del Proposal a obtener", example = "1")
            Long id
    );

    @Operation(summary = "Obtener todas las  propuestas por su Adviser", responses = {
        @ApiResponse(
            description = "Proposals devueltos exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProposalResponseDTO.class))
            )
        ),
        @ApiResponse(
            description = "Fallo al buscar los Proposals",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "No autorizado, el token es inválido",
            responseCode = "401",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    }, parameters = @Parameter(
        in = ParameterIn.HEADER,
        name = "X-Token",
        description = "Token del usuario",
        schema = @Schema(implementation = String.class),
        required = true
    ))
    ResponseEntity<Collection<ProposalResponseDTO>> getAllByAdviserId(
        Long userId,
        @Parameter(description = "ID del Adviser de las Proposals a obtener", example = "1")
            Long id
    );

}
