package com.scaler.productservicedec24.controlleradvices;

import com.scaler.productservicedec24.dtos.ProductNotFoundExceptionDto;
import com.scaler.productservicedec24.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException e) {
        ProductNotFoundExceptionDto productNotFoundExceptionDto = new ProductNotFoundExceptionDto();
        productNotFoundExceptionDto.setMessage("Product not found for id"+ e.getProductId());
        productNotFoundExceptionDto.setResolution("Try passing a valid product id");

        return new ResponseEntity<>(productNotFoundExceptionDto, HttpStatus.NOT_FOUND);
    }


    /// Here the hierarchy doesnt matter
    @ExceptionHandler(RuntimeException.class)
    public void handleRuntimeException() {
        System.out.println("Runtime exception occurred");
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public void handleArrayIndexOutOfBoundsException() {
        System.out.println("Array index out of bounds exception occurred");
    }
}
