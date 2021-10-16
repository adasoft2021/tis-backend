package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.domain.Publication;
import com.adasoft.tis.dto.publication.CreatePublicationDTO;
import com.adasoft.tis.dto.publication.PublicationResponseDTO;
import com.adasoft.tis.dto.publication.UpdatePublicationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@Tag(name = "PublicationRestController", description = "Controlador para gestionar las Publicaciones")
public interface PublicationRestController {
    @Operation(summary = "Crear una publicación", responses = {
        @ApiResponse(
            description = "Publicación creada exitosamente",
            responseCode = "201",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = PublicationResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Campos no válidos",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "El ID del Asesor no existe en el sistema",
            responseCode = "404",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<PublicationResponseDTO> create(
        @RequestBody(description = "PublicationDTO que contiene los nuevos datos a crear")
            CreatePublicationDTO publicationDTO
    );

    @Operation(summary = "Actualizar una publicación por su ID", responses = {
        @ApiResponse(
            description = "Publicación actualizado exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = PublicationResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Campos no válidos",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "No se encontró el ID del Publication en el sistema",
            responseCode = "404",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<PublicationResponseDTO> update(
        @Parameter(description = "ID del Publication a actualizar", example = "1")
            Long id,
        @RequestBody(description = "PublicationDTO que contiene los nuevos datos a ser actualizados")
            UpdatePublicationDTO publicationDTO);

    @Operation(summary = "Elimnar una publicación por su ID", responses = {
        @ApiResponse(
            description = "Publicación eliminada exitosamente",
            responseCode = "204"
        ),
        @ApiResponse(
            description = "No se encontró el ID del Publication en el sistema",
            responseCode = "404",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "No se puede eliminar la Publicación con el ID dado",
            responseCode = "406",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<?> delete(@Parameter(description = "ID del Publication a eliminar", example = "1") Long id);

    @Operation(summary = "Obtener lista publicaciones por ID del Asesor y tipo de Publicación", responses = {
        @ApiResponse(
            description = "Publicaciones obtenidas exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = PublicationResponseDTO.class))
            )
        ),
        @ApiResponse(
            description = "No se encontró el ID del Asesor en el sistema",
            responseCode = "404",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<Collection<PublicationResponseDTO>> getByAdviserId(
        @Parameter(description = "ID del Adviser a obtener sus publicaciones", example = "1")
            Long adviserId,
        @Parameter(description = "Tipo de Publicación a obtener", example = "1")
            Publication.PublicationType type);
}
