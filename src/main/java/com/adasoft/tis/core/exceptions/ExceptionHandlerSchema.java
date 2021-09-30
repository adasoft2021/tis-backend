package com.adasoft.tis.core.exceptions;

import org.springframework.http.HttpStatus;

import java.lang.annotation.*;

/**
 * Representa el esquema común de un problema en el sistema.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface ExceptionHandlerSchema {

    /**
     * Obtenga el código de estado asociado.
     *
     * @return el código de estado.
     */
    HttpStatus code();

    /**
     * Obtenga el título asociado del problema.
     *
     * @return el título del problema.
     */
    String title();
}
