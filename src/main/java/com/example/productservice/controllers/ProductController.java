package com.example.productservice.controllers;

import com.example.productservice.DTOs.UserDTO;
import com.example.productservice.commons.AuthCommons;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private AuthCommons authCommons;

    public ProductController(ProductService productService,AuthCommons authCommons){
        this.productService=productService;
        this.authCommons=authCommons;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id,
                                         @RequestHeader("authToken") String token) throws ProductNotFoundException {
        UserDTO userDTO=authCommons.validate(token);
        ResponseEntity<Product> responseEntity;
        if(userDTO==null){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        Product product=productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
        //return null;
    }

    @GetMapping("")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping()
    public void createProduct(@RequestBody Product product){
        productService.createProduct(product);
    }

    @PostMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProductById(id,product);
    }
}
