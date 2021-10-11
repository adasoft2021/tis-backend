package com.adasoft.tis.core.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@ExceptionHandlerSchema(code = HttpStatus.CONFLICT, title = EntityAlreadyExistException.TITLE)
public class EntityAlreadyExistException extends TisDomainException {

    public static final String TITLE = "La entidad ya existe";
    private static final long serialVersionUID = -4339570733812642070L;

    private static final String EXCEPTION_DETAIL_MESSAGE = "%s con id %s ya existe.";

    private final Class<?> entityClass;
    private final Serializable entityId;

    public EntityAlreadyExistException(final Class<?> entityClass, final Serializable entityId) {
        this(entityClass, entityId, null);
    }

    public EntityAlreadyExistException(
            final Class<?> entityClass,
            final Serializable entityId,
            final Throwable exception) {
        super(exception);

        checkArgument(entityClass != null, "El parámetro de la clase de entidad no debe ser nulo.");
        checkArgument(entityId != null, "El parámetro EntityId no debe ser nulo.");

        this.entityClass = entityClass;
        this.entityId = entityId;
    }

    @Override
    protected String getExceptionDetail() {
        return String.format(EXCEPTION_DETAIL_MESSAGE, entityClass.getSimpleName(), entityId);
    }

}
