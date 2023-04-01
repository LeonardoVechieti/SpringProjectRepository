package br.com.leonardovechieti.vendasproject.rest.controller;

import br.com.leonardovechieti.vendasproject.exception.PedidoNaoEncontradoException;
import br.com.leonardovechieti.vendasproject.exception.RegraNegocioException;
import br.com.leonardovechieti.vendasproject.rest.ApiErrors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;


@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler (RegraNegocioException.class)
    @ResponseStatus (BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String messageError = ex.getMessage();
        return new ApiErrors(messageError);
    }

    @ExceptionHandler (PedidoNaoEncontradoException.class)
    @ResponseStatus (NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex){
        String messageError = ex.getMessage();
        return new ApiErrors(messageError);
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    @ResponseStatus (BAD_REQUEST)
    public ApiErrors handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map (erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }
}
