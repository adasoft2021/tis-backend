package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.dto.company.CompanyResponseDTO;
import com.adasoft.tis.dto.company.CreateCompanyDTO;
import com.adasoft.tis.dto.company.UpdateCompanyDTO;
import com.adasoft.tis.dto.user.UserResponseDTO;
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

@Tag(name = "CompanyRestController", description = "Controlador para gestionar Companys")
public interface CompanyRestController {
    @Operation(summary = "Creación de Company", responses = {
        @ApiResponse(
            description = "Company creado exitosamente",
            responseCode = "201",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al crear Company",
            responseCode = "400",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            description = "Ya existe esta Company a crear",
            responseCode = "409",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<CompanyResponseDTO> create(
        @Parameter(description = "Codigo de Registro asociado al asesor", example = "abc-cde-fgh")
            String registrationCode,
        @RequestBody(description = "CompanyDTO que contiene los nuevos datos a crear")
            CreateCompanyDTO companyDTO
    );

    @Operation(summary = "Obtener un Company por su ID", responses = {
        @ApiResponse(
            description = "Company devuelto exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = CompanyResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al buscar Company",
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
            description = "No se encontró el ID de Company en el sistema",
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
    ResponseEntity<CompanyResponseDTO> get(
        Long userId,
        @Parameter(description = "ID de Company a obtener", example = "1")
            Long id
    );

    @Operation(summary = "Actualización de Company por su ID", responses = {
        @ApiResponse(
            description = "Company actualizado exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = CompanyResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al actualizar el Company",
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
            description = "No se encontró el ID del Company en el sistema",
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
    ResponseEntity<CompanyResponseDTO> update(
        Long userId,
        @Parameter(description = "ID del Company a actualizar", example = "1")
            Long id,
        @RequestBody(description = "CompanyDTO que contiene los nuevos datos a ser actualizados")
            UpdateCompanyDTO companyDTO
    );

    @Operation(summary = "Eliminacion de un Company por su ID", responses = {
        @ApiResponse(
            description = "Company eliminado exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = CompanyResponseDTO.class)
            )
        ),
        @ApiResponse(
            description = "Fallo al eliminar el Company",
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
            description = "No se encontró el ID del Company en el sistema",
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
    ResponseEntity<CompanyResponseDTO> delete(
        Long userId,
        @Parameter(description = "ID del Company a eliminar", example = "1")
            Long id
    );

    @Operation(summary = "Obtener todas los Company", responses = {
        @ApiResponse(
            description = "Companys devueltos exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CompanyResponseDTO.class))
            )
        ),
    })
    ResponseEntity<Collection<CompanyResponseDTO>> getAll();
}
