package com.adasoft.tis.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerController {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFound(final EntityNotFoundException exception) {
        return ResponseEntity.status(exception.getCode()).body(exception.getExceptionResponse());
    }
    @ExceptionHandler(EntityConflictException.class)
    public ResponseEntity<ExceptionResponse> handleEntityConflict(final EntityConflictException exception) {
        return ResponseEntity.status(exception.getCode()).body(exception.getExceptionResponse());
    }
}
