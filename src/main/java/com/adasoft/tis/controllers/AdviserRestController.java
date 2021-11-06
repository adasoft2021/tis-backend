package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.dto.adviser.AdviserResponseDTO;
import com.adasoft.tis.dto.adviser.CreateAdviserDTO;
import com.adasoft.tis.dto.adviser.UpdateAdviserDTO;
import com.adasoft.tis.dto.classCode.ClassCodeResponseDTO;
import com.adasoft.tis.dto.spaceAnswer.SpaceAnswerResponseDTO;
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

@Tag(name = "AdviserRestController", description = "Controlador para gestionar Advisers")
public interface AdviserRestController {
    @Operation(summary = "Creación de Adviser", responses = {
        @ApiResponse(
            description = "Adviser creado exitosamente",
            responseCode = "201",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = AdviserResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al crear Adviser",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<AdviserResponseDTO> create(
        @RequestBody(description = "AdviserDTO que contiene los nuevos datos a crear")
            CreateAdviserDTO adviserDTO
    );

    @Operation(summary = "Obtener un Adviser por su ID", responses = {
        @ApiResponse(
            description = "Adviser devuelto exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = AdviserResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al buscar Adviser",
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
            description = "No se encontró el ID de Adviser en el sistema",
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
    ResponseEntity<AdviserResponseDTO> get(
        Long userId,
        @Parameter(description = "ID de Adviser a obtener", example = "1")
            Long id
    );

    @Operation(summary = "Actualización de Adviser por su ID", responses = {
        @ApiResponse(
            description = "Adviser actualizado exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = AdviserResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al actualizar el Adviser",
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
            description = "No se encontró el ID del Adviser en el sistema",
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
    ResponseEntity<AdviserResponseDTO> update(
        Long userId,
        @Parameter(description = "ID del Adviser a actualizar", example = "1")
            Long id,
        @RequestBody(description = "AdviserDTO que contiene los nuevos datos a ser actualizados")
            UpdateAdviserDTO adviserDTO
    );

    @Operation(summary = "Eliminacion de un Adviser por su ID", responses = {
        @ApiResponse(
            description = "Adviser eliminado exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = AdviserResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al eliminar el Adviser",
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
            description = "No se encontró el ID del Adviser en el sistema",
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
    ResponseEntity<AdviserResponseDTO> delete(
        Long userId,
        @Parameter(description = "ID del Adviser a eliminar", example = "1")
            Long id
    );

    @Operation(summary = "Obtener todas los Adviser", responses = {
        @ApiResponse(
            description = "Advisers devueltos exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = AdviserResponseDTO.class))
            )
        ),
        @ApiResponse(
            description = "Fallo al buscar los Advisers",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),

    })
    ResponseEntity<Collection<AdviserResponseDTO>> getAll();

    @Operation(summary = "Crear un ClassCode por el ID del Adviser", responses = {
        @ApiResponse(
            description = "ClassCode creado exitosamente",
            responseCode = "201",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ClassCodeResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al buscar Adviser",
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
            description = "No se encontró el ID de Adviser en el sistema",
            responseCode = "404",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
    }, parameters = @Parameter(
        in = ParameterIn.HEADER,
        name = "X-Token",
        description = "Token del usuario",
        schema = @Schema(implementation = String.class),
        required = true
    ))
    ResponseEntity<ClassCodeResponseDTO> createClassCode(
        Long userId,
        @Parameter(description = "ID de Adviser para crear ClassCode", example = "1")
            Long adviserId
    );


    @Operation(summary = "Obtener las SpaceAnswer de un space del adviser", responses = {
        @ApiResponse(
            description = "SpaceAnswers devueltos exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = SpaceAnswerResponseDTO.class))
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
            description = "No existe la entidad",
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
    ResponseEntity<Collection<SpaceAnswerResponseDTO>> getSpaceAnswers(
        Long userId,
        @Parameter(description = "ID del adviser") Long adviserId,
        @Parameter(description = "ID del space") Long spaceId
    );

}
