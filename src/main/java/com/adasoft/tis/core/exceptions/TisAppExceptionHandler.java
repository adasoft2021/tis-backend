package com.adasoft.tis.core.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

/**
 * Manejador de excepciones que genera la respuesta de error del problema de la aplicación.
 */
@Slf4j
public class TisAppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TisDomainException.class)
    public ResponseEntity<Problem> handleDomainException(final TisDomainException exception) {
        ExceptionHandlerSchema exceptionHandler = AnnotationUtils
                .findAnnotation(exception.getClass(), ExceptionHandlerSchema.class);

        validateException(exception, exceptionHandler);

        int status = getStatus(exception, exceptionHandler);
        String title = getTitle(exception, exceptionHandler);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .title(title)
                .message(exception.getExceptionDetail())
                .build();

        return ResponseEntity.status(status).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException exception,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {
        logger.error("Se ha producido una excepción de validación: "
                + exception.getBindingResult(), exception);

        String errorDetail = "";
        if (exception.hasFieldErrors()) {
            errorDetail = exception.getFieldErrors().stream()
                    .map(error -> String.format("[%s %s]", error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.joining(", "));
        }

        ErrorResponse errorResponse = ErrorResponse.builder()
                .title("Las validaciones de la entidad no han pasado.")
                .message(errorDetail)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private void validateException(final TisDomainException exception, final ExceptionHandlerSchema exceptionHandler) {
        if (!(exception instanceof DefaultTisDomainException) &&
                null == exceptionHandler) {
            log.error("La clase de problema no tiene la anotación @ExceptionHandlerSchema.");

            String errorFormat = "Se requiere la anotación @ExceptionHandlerSchema en la clase de problema: '%s'";
            throw new UnsupportedOperationException(String.format(errorFormat, exception.getClass()));
        }

        if (!(exception instanceof DefaultTisDomainException) &&
                !StringUtils.hasText(exceptionHandler.title())) {

            log.error("La anotación @ExceptionHandlerSchema no contiene el título del problema.");

            throw new UnsupportedOperationException(
                    "El campo de título en la anotación @ExceptionHandlerSchema no puede estar vacío");
        }
    }

    private int getStatus(final TisDomainException exception, final ExceptionHandlerSchema exceptionHandler) {
        int response;

        if (exception instanceof DefaultTisDomainException) {
            response = ((DefaultTisDomainException) exception).getStatus().value();
        } else {
            response = exceptionHandler.code().value();
        }

        return response;
    }

    private String getTitle(final TisDomainException exception, final ExceptionHandlerSchema exceptionHandler) {
        String titleResponse;

        if (exception instanceof DefaultTisDomainException) {
            titleResponse = DefaultTisDomainException.TITLE;
        } else {
            titleResponse = exceptionHandler.title();
        }

        return titleResponse;
    }
}
