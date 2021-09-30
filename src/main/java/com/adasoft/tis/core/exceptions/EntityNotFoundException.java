package com.adasoft.tis.core.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class EntityNotFoundException extends DomainException{
    public static final String TITLE="La entidad no pudo ser encontrada";
    private static final long serialVersionUID= -8275350855508595579L;
    private static final String EXCEPTION_DETAIL_MESSAGE="%s con id %s no puede ser encontrado o no existe";
    private final Class<?> entityClass;
    private final Serializable entityID;

    public EntityNotFoundException(final Class<?> ec,final Serializable id){
        this(ec,id,null);
    }

    public EntityNotFoundException(final Class<?> ec,final Serializable id,final Throwable exception){
        super(exception);
        entityClass=ec;
        entityID=id;
    }

    @Override
    protected String getExceptionDetail() {
        return String.format(EXCEPTION_DETAIL_MESSAGE,entityClass.getSimpleName(),entityID);
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public ExceptionResponse getExceptionResponse() {
        ExceptionResponse response = new ExceptionResponse();
        response.setTitle(TITLE);
        response.setMessage(getExceptionDetail());
        return response;
    }
}
