package com.eclesia.gestao_ministerial.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ImagemException  extends RuntimeException{

    public ImagemException( String mensagem) {
    super(mensagem);
    }

    @ExceptionHandler(ImagemException.class)
    public String ImagemGrandeExceptio(){
        return super.getMessage();
    }
}
