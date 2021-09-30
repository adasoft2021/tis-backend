package com.adasoft.tis.core.exceptions;

import org.springframework.http.HttpStatus;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

public final class DefaultTisDomainException extends TisDomainException {

    public static final String TITLE = "El proceso no puede continuar";
    private static final long serialVersionUID = -4509438718207343347L;

    private final String errorMessage;
    private final HttpStatus status;

    public DefaultTisDomainException(final HttpStatus status, final String errorMessage) {
        this(status, errorMessage, null);
    }

    public DefaultTisDomainException(final HttpStatus status, final String errorMessage, final Throwable exception) {
        super(exception);

        checkArgument(status != null, "El parámetro de estado no debe ser nulo.");
        checkArgument(errorMessage != null, "El parámetro ErrorMessage no debe ser nulo.");

        this.status = status;
        this.errorMessage = errorMessage;
    }

    protected HttpStatus getStatus() {
        return status;
    }

    @Override
    protected String getExceptionDetail() {
        return errorMessage;
    }

}
