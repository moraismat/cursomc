package com.aula.cursomc.services.exception;

import javax.persistence.criteria.CriteriaBuilder.Case;

public class ObjectNotFoundException extends RuntimeException{
    
    private static final long serialVersionUD = 1L;

    public ObjectNotFoundException(String msg){
        super(msg);
    }

    public ObjectNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }   
}