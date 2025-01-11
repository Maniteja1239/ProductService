package com.example.productservice.service;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.models.Category;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optional= productRepository.findById(id);
        if(optional.isEmpty()){
            throw new ProductNotFoundException(id);
        }
        return optional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProductById(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> optional=productRepository.findById(id);
        if(optional.isEmpty()){
            throw new ProductNotFoundException(id);
        }
        Product p=optional.get();
        if(product.getPrice()!=null){
            p.setPrice(product.getPrice());
        }
        if(product.getImage()!=null){
            p.setImage(product.getImage());
        }
        if(product.getTitle()!=null){
            p.setTitle(product.getTitle());
        }
        return productRepository.save(p);
    }

    @Override
    public Product createProduct(Product product) {
        Category category=product.getCategory();
        if(category.getId()==null){
            Category savedCatogery=categoryRepository.save(category);
            product.setCategory(savedCatogery);
            return productRepository.save(product);
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {

    }
}
