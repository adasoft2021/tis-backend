package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.dto.observation.CreateObservationDTO;
import com.adasoft.tis.dto.observation.ObservationResponseDTO;
import com.adasoft.tis.dto.observation.UpdateObservationDTO;
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

@Tag(name = "ObservationRestController", description = "Controlador para gestionar las Observaciones")
public interface ObservationRestController {
    @Operation(summary = "Creación de una observacion", responses = {
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
    ResponseEntity<ObservationResponseDTO> create(
        Long userId,
        @Parameter(description = "ID de Review del cual se crea una Observation", example = "1")
            Long reviewId,
        @RequestBody(description = "ObservationDTO que contiene los nuevos datos a crear")
            CreateObservationDTO observationDTO
    );

    @Operation(summary = "Obtener de una observacion por su ID", responses = {
        @ApiResponse(
            description = "Observation devuelto exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ObservationResponseDTO.class)
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
            description = "No autorizado, el token es inválido",
            responseCode = "401",
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
    }, parameters = @Parameter(
        in = ParameterIn.HEADER,
        name = "X-Token",
        description = "Token del usuario",
        schema = @Schema(implementation = String.class),
        required = true
    ))
    ResponseEntity<ObservationResponseDTO> get(
        Long userId,
        @Parameter(description = "ID de la Observation a obtener", example = "1")
            Long id
    );

    @Operation(summary = "Actualización de una observacion por su ID", responses = {
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
            description = "No autorizado, el token es inválido",
            responseCode = "401",
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
    }, parameters = @Parameter(
        in = ParameterIn.HEADER,
        name = "X-Token",
        description = "Token del usuario",
        schema = @Schema(implementation = String.class),
        required = true
    ))
    ResponseEntity<ObservationResponseDTO> update(
        Long userId,
        @Parameter(description = "ID del Observation a actualizar", example = "1")
            Long id,
        @RequestBody(description = "ObservationDTO que contiene los nuevos datos a ser actualizados")
            UpdateObservationDTO observationDTO
    );

    @Operation(summary = "Eliminacion de una observacion por su ID", responses = {
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
            description = "No autorizado, el token es inválido",
            responseCode = "401",
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
    }, parameters = @Parameter(
        in = ParameterIn.HEADER,
        name = "X-Token",
        description = "Token del usuario",
        schema = @Schema(implementation = String.class),
        required = true
    ))
    ResponseEntity<ObservationResponseDTO> delete(
        Long userId,
        @Parameter(description = "ID del Observation a eliminar", example = "1")
            Long id
    );

    @Operation(summary = "Obtener todas las  observaciones por su propuesta", responses = {
        @ApiResponse(
            description = "Observations devueltos exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ObservationResponseDTO.class))

            )
        ),
        @ApiResponse(
            description = "Fallo al buscar los Observations",
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
    ResponseEntity<Collection<ObservationResponseDTO>> getAllByReviewId(
        Long userId,
        @Parameter(description = "ID del Review de las Observations a obtener", example = "1")
            Long reviewId
    );
}
