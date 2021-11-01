package com.adasoft.tis.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserAutController", description = "Controlador para autenticar usuarios")
public interface UserAutController {
    @Operation(summary = "Enviar un mensaje", responses = {
        @ApiResponse(
            description = "Realizado exitosamente",
            responseCode = "200"
        )
    })
    public String createMail(
        @Parameter(description = "email al cual enviar un mensaje", example = "alguien@email.com")
            String email);

    @Operation(summary = "Comprobar usuario con token", responses = {
        @ApiResponse(
            description = "Realizado exitosamente",
            responseCode = "200"
        )
    })
    public String check(
        @Parameter(description = "email al cual enviar un mensaje", example = "alguien@email.com")
            String email,
        @Parameter(description = "token recibido por email", example = "afggdfgdfgg")
            String token
    );
}
