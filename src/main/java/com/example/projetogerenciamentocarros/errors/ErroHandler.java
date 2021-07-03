package com.example.projetogerenciamentocarros.errors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice                           //Assim que houver algum erro, o spring chama essa classe interseptadora.
public class ErroHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception .class)
    public List<ErroFormularioDto> handle(BindException exception){
        List<ErroFormularioDto> dto  = new ArrayList<>();
        List<FieldError> filedErrors = exception.getBindingResult().getFieldErrors();
        filedErrors.forEach(e ->{
            String mensagem =messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroFormularioDto erro = new ErroFormularioDto(e.getField(),mensagem);
            dto.add(erro);
        });
        return dto;

    }

}
