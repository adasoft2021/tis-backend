package com.adasoft.tis.core.utils;

import com.adasoft.tis.core.exceptions.DefaultTisDomainException;
import org.springframework.http.HttpStatus;

/**
 * Una clase estática que contiene una lógica de utilidad común que se puede utilizar en cualquier otra clase.
 */
public final class Preconditions {

    /**
     * Esta clase no debe ser instanciable en ningún lugar.
     */
    private Preconditions() {
    }

    /**
     * Verifica si la expresión cumple la condición. Si no es así, lanza una excepción.
     *
     * @param expression   la expresión requerida evaluada en el cliente.
     * @param errorMessage el mensaje de error para establecer si no cumple con la condición.
     * @throws IllegalArgumentException cuando no cumple la condición.
     */
    public static void checkArgument(final boolean expression, final String errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkUserId(final Long userId, final Long idToCompare) {
        if (!userId.equals(idToCompare)) {
            throw new DefaultTisDomainException(
                HttpStatus.UNAUTHORIZED,
                "Usted no tiene autorización para realizar esta acción.");
        }
    }
}
