package org.example.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

// apply this class to all classes annotated with @Controllers or @RestControllers
// Information will be written directly to @ResponseBody in Json/XML
// alternative: @ControllerAdvice that will write HTML

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handlemethodArgumentNotValid(MethodArgumentNotValidException exception) {
        //TODO choose to return custome object here - transformed to json/xml
        return "Sorry, Bad Requests " + exception.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolation(ConstraintViolationException exception) {
        //TODO choose to return custome object here - transformed to json/xml
        return "Sorry, please enter correct values " + exception.getMessage();
    }
}
