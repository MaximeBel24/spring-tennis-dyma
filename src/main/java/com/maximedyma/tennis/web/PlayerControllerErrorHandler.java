package com.maximedyma.tennis.web;

import com.maximedyma.tennis.Error;
import com.maximedyma.tennis.service.PlayerAlreadyExistsException;
import com.maximedyma.tennis.service.PlayerDataRetrievalException;
import com.maximedyma.tennis.service.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class PlayerControllerErrorHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleNoElementException(PlayerNotFoundException ex){
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(PlayerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handlePlayerAlreadyExistsException(PlayerAlreadyExistsException ex) {
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(PlayerDataRetrievalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handlePlayerDataRetrievalException(PlayerDataRetrievalException ex) {
        return new Error(ex.getMessage());
    }
}
