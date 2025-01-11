package com.example.productservice.service;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;

import java.util.List;


public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product updateProductById(Long id, Product product) throws ProductNotFoundException;
    Product createProduct(Product product);
    void deleteProductById(Long id);
}
