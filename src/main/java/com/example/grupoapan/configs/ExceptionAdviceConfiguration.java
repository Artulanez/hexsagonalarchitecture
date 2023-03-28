package com.example.grupoapan.configs;

import com.example.grupoapan.repositories.NotFoundException;
import com.example.grupoapan.transportlayers.openapi.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdviceConfiguration {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundHandler(NotFoundException e) {
        ErrorMessage generalError = new ErrorMessage();
        generalError.setStatus(404);
        generalError.setMessage(e.getMessage());
        return generalError;
    }

}
