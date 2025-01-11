package com.example.productservice.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends Exception{
    private Long id;
    public ProductNotFoundException(Long id){
        super();
        this.id=id;
    }
}
