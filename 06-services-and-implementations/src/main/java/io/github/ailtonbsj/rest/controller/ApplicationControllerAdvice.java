package io.github.ailtonbsj.rest.controller;

import io.github.ailtonbsj.exception.BussinessRoleException;
import io.github.ailtonbsj.exception.OrderNotFoundException;
import io.github.ailtonbsj.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(BussinessRoleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBussinessRoleException(BussinessRoleException ex){
        String messageError = ex.getMessage();
        return  new ApiErrors(messageError);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleOrderNotFoundException(OrderNotFoundException ex){
        return  new ApiErrors(ex.getMessage());
    }
}
