package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.dto.review.CreateReviewDTO;
import com.adasoft.tis.dto.review.ReviewResponseDTO;
import com.adasoft.tis.dto.review.UpdateReviewDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "ReviewRestController", description = "Controlador para gestionar las Revisiones")
public interface ReviewRestController {
    @Operation(summary = "Obtener una revisión por su ID", responses = {
        @ApiResponse(
            description = "Review obtenido exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ReviewResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "No se encontró el ID del Review en el sistema",
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
    ResponseEntity<ReviewResponseDTO> get(
        Long userId,
        @Parameter(description = "ID del Review a obtener", example = "1")
            Long id
    );

    @Operation(summary = "Creación de una revisión", responses = {
        @ApiResponse(
            description = "Review creado exitosamente",
            responseCode = "201",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ReviewResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al crear el Review",
            responseCode = "400",
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
    ResponseEntity<ReviewResponseDTO> create(
        Long userId,
        @RequestBody(description = "ReviewDTO que contiene los nuevos datos a crear")
            CreateReviewDTO reviewDTO
    );

    @Operation(summary = "Actualización de una revisión por su ID", responses = {
        @ApiResponse(
            description = "Review actualizado exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ReviewResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al actualizar el Review",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "No se encontró el ID del Review o del Qualification en el sistema",
            responseCode = "404",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "Ya se realizó la revisión de todas las notas parciales",
            responseCode = "405",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "Intento de modificar otra calificación que no pertenece a la revisión",
            responseCode = "406",
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
    ResponseEntity<ReviewResponseDTO> update(
        Long userId,
        @Parameter(description = "ID del Review a actualizar", example = "1")
            Long id,
        @RequestBody(description = "ReviewDTO que contiene los nuevos datos a ser actualizados")
            UpdateReviewDTO reviewDTO
    );
}
