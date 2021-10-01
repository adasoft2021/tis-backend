package com.adasoft.tis.core.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@ExceptionHandlerSchema(code = HttpStatus.NOT_FOUND, title = EntityNotFoundException.TITLE)
public class EntityNotFoundException extends TisDomainException {

    public static final String TITLE = "No se pudo encontrar la entidad";
    private static final long serialVersionUID = -9093937452666187533L;

    private static final String EXCEPTION_DETAIL_MESSAGE = "%s con id %s no se pudo encontrar o no existe.";

    private final Class<?> entityClass;
    private final Serializable entityId;

    public EntityNotFoundException(Class<?> entityClass, Serializable entityId) {
        this(entityClass, entityId, null);
    }

    public EntityNotFoundException(Class<?> entityClass, Serializable entityId, Throwable exception) {
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
