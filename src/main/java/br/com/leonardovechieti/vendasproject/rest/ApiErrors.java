package br.com.leonardovechieti.vendasproject.rest;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;


@Data
@NoArgsConstructor
public class ApiErrors {
    @Getter
    private List<String> errors;

    public ApiErrors(String messageError) {
        this.errors = Arrays.asList(messageError);
    }
}
