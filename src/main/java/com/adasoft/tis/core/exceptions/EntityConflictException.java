package com.adasoft.tis.core.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class EntityConflictException extends DomainException{

    public static final String TITLE="La entidad ya existe";
    private static final long serialVersionUID= -5847758294920684522L;
    private static final String EXCEPTION_DETAIL_MESSAGE="%s con id %s no puede ser creada por que ya existe";
    private final Class<?> entityClass;
    private final Serializable entityID;

    public EntityConflictException(final Class<?> ec,final Serializable id){
        this(ec,id,null);
    }

    public EntityConflictException(final Class<?> ec,final Serializable id,final Throwable exception){
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
