package com.example.productservice.exceptionHandlers;

import com.example.productservice.DTOs.ProductNotFoundExceptionHandlerDTO;
import com.example.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionHandlerDTO> ProductNotFoundExceptionHandler(ProductNotFoundException exception){
        ProductNotFoundExceptionHandlerDTO productNotFoundExceptionHandlerDTO=new ProductNotFoundExceptionHandlerDTO();
        productNotFoundExceptionHandlerDTO.setMessage("Product with id: "+exception.getId()+" does not exist");
        ResponseEntity<ProductNotFoundExceptionHandlerDTO> responseEntity=new ResponseEntity<>(productNotFoundExceptionHandlerDTO, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
