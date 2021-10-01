package com.adasoft.tis.core.exceptions;

public abstract class TisDomainException extends RuntimeException {

    private static final long serialVersionUID = -798332257254642587L;

    protected TisDomainException(Throwable exception) {
        super(exception);
    }

    protected abstract String getExceptionDetail();
}
