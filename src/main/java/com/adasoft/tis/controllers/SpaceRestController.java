package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.dto.spaceAnswer.CreateSpaceAnswerDTO;
import com.adasoft.tis.dto.spaceAnswer.SpaceAnswerResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;


@Tag(name = "SpaceRestController", description = "Controlador para gestionar Space,SpaceAnswer")
public interface SpaceRestController {
    @Operation(summary = "Crear SpaceAnswer", responses = {
        @ApiResponse(
            description = "SpaceAnswer creado exitosamente",
            responseCode = "201",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = SpaceAnswerResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al crear SpaceAnswer",
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
            description = "Space no encontrado",
            responseCode = "404",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "Company no encontrado",
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
    ResponseEntity<SpaceAnswerResponseDTO> createSpaceAnswer(
        Long userId,
        @Parameter(description = "ID de Space") Long spaceId,
        @RequestBody(description = "DTO de SpaceAnswer a crear") CreateSpaceAnswerDTO createSpaceAnswerDTO);
}
