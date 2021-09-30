package com.adasoft.tis.core.exceptions;

import org.springframework.http.HttpStatus;

public abstract class DomainException extends RuntimeException {
    private static final long serialVersionUID = -7316308085959601021L;

    protected DomainException(final Throwable exception) {
        super(exception);
    }

    protected abstract String getExceptionDetail();

    public abstract HttpStatus getCode();

    public abstract ExceptionResponse getExceptionResponse();

}
