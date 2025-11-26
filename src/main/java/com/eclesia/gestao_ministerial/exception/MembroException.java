package com.eclesia.gestao_ministerial.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


public class MembroException extends RuntimeException {

    public MembroException(String message) {
        super(message);
    }

    @ExceptionHandler(MembroException.class)
    public String membroJaCadastrado(){
        return super.getMessage();
    }
}
