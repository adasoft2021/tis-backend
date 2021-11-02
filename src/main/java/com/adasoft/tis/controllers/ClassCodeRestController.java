package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.ErrorResponse;
import com.adasoft.tis.dto.classCode.CreateClassCodeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "ClassCodeRestController", description = "Controlador para gestionar los Códigos de clase")
public interface ClassCodeRestController {
    @Operation(summary = "Validar código de clase", responses = {
        @ApiResponse(
            description = "Código de clase válido",
            responseCode = "204"
        ),
        @ApiResponse(
            description = "No se encontró el Código de Clase en el sistema",
            responseCode = "404",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    ResponseEntity<?> validateClassCode(
        @RequestBody(description = "CreateClassCodeDTO que contiene el código de clase a validar")
            CreateClassCodeDTO classCodeDTO
    );
}
