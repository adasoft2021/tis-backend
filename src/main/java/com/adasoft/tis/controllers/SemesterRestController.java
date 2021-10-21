package com.adasoft.tis.controllers;

import com.adasoft.tis.dto.semester.SemesterResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "SemesterRestController", description = "Controlador para gestionar semestre")
public interface SemesterRestController {
    @Operation(summary = "Obtener el semestre actual", responses = {
        @ApiResponse(
            description = "Semestre retornado exitosamente",
            responseCode = "200",
            content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = String.class)
            )
        )
    })
    ResponseEntity<SemesterResponseDTO> get();
}
