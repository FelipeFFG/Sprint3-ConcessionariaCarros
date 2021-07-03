package com.example.projetogerenciamentocarros.errors;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)  //404
public class ErrorToFoundValues extends  RuntimeException{

    public ErrorToFoundValues(String mensagem){
        super(mensagem);
    }
}
