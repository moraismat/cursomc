package com.aula.cursomc.services.exception;

public class DataIntegrityException extends RuntimeException{
    private static final long serialVersionUD = 1L;

    public DataIntegrityException(String msg){
        super(msg);
    }

    public DataIntegrityException(String msg, Throwable cause){
        super(msg, cause);
    }   
}