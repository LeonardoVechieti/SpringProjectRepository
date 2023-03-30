package br.com.leonardovechieti.vendasproject.rest.controller;

import br.com.leonardovechieti.vendasproject.exception.RegraNegocioException;
import br.com.leonardovechieti.vendasproject.rest.ApiErrors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static org.springframework.http.HttpStatus.*;


@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler (RegraNegocioException.class)
    @ResponseStatus (BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String messageError = ex.getMessage();
        return new ApiErrors(messageError);
    }
}
