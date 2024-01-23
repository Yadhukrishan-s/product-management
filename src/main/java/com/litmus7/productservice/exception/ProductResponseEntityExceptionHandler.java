package com.litmus7.productservice.exception;

import com.litmus7.productservice.error.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@ControllerAdvice
public class ProductResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotfoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(ProductNotfoundException ex, WebRequest request) throws Exception {
        return new ResponseEntity<>(new ErrorDetails(LocalDate.now(), ex.getMessage(), HttpStatus.NOT_FOUND, request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductnotCreatedException.class)
    public final ResponseEntity<ErrorDetails> handleProductNotCreatedException(ProductnotCreatedException ex, WebRequest request) throws Exception {
        return new ResponseEntity<>(new ErrorDetails(LocalDate.now(), ex.getMessage(), HttpStatus.BAD_REQUEST, request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(LocalDate.now(), ex.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST, request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
        return new ResponseEntity<>(new ErrorDetails(LocalDate.now(), ex.getMessage(), HttpStatus.BAD_REQUEST, request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmptyListException.class)
    public final ResponseEntity<ErrorDetails> handleEmptyListException(EmptyListException ex, WebRequest request) throws Exception {
        return new ResponseEntity<>(new ErrorDetails(LocalDate.now(), ex.getMessage(), HttpStatus.BAD_REQUEST, request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
