package com.clinic.system.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity customException(CustomException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());}

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElement(NoSuchElementException ex){
        return ResponseEntity.badRequest().body( ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorValidation::new).toList());
    }

    private record DataErrorValidation(String campo, String message) {
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
