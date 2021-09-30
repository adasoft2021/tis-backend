package com.adasoft.tis.core.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;

@ExceptionHandlerSchema(code = HttpStatus.BAD_REQUEST, title = UnableToDeleteEntityException.TITLE)
public class UnableToDeleteEntityException extends TisDomainException {

    public static final String TITLE = "No se pudo eliminar la entidad";

    private static final long serialVersionUID = 4690177480494587739L;
    private static final String EXCEPTION_DETAIL_MESSAGE = "%s con id %s no se pudo eliminar.";

    private Class<?> entityClass;
    private Serializable entityId;

    public UnableToDeleteEntityException(Class<?> entityClass, Serializable entityId) {
        this(entityClass, entityId, null);
    }

    public UnableToDeleteEntityException(Class<?> entityClass, Serializable entityId, Throwable exception) {
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
