package com.adasoft.tis.core.exceptions;

import org.springframework.http.HttpStatus;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@ExceptionHandlerSchema(code = HttpStatus.PRECONDITION_FAILED, title = PreConditionException.TITLE)
public class PreConditionException extends TisDomainException {

    public static final String TITLE = "La información no cumple con las condiciones previas requeridas";

    private final String errorMessage;


    public PreConditionException(final String errorMessage) {
        this(errorMessage, null);
    }

    public PreConditionException(final String errorMessage, final Throwable exception) {
        super(exception);

        checkArgument(errorMessage != null, "El parámetro del mensaje de error no debe ser nulo.");

        this.errorMessage = errorMessage;
    }


    @Override
    protected String getExceptionDetail() {
        return errorMessage;
    }
}
