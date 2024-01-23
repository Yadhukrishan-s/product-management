package com.litmus7.productservice.exception;


public class ProductNotfoundException extends RuntimeException {
    public ProductNotfoundException(String message) {
        super(message);
    }
}
