package com.example.productservice.service;

import com.example.productservice.DTOs.FakeStoreProductDTO;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public Product convertToProduct(FakeStoreProductDTO fakeStoreProductDTO){
        Product product=new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImage(fakeStoreProductDTO.getImage());
        Category category=new Category();
        category.setDescription(fakeStoreProductDTO.getDescription());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDTO fakeStoreProductDTO=restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDTO.class);
        if(fakeStoreProductDTO==null){
            return null;
        }
        return convertToProduct(fakeStoreProductDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products=new ArrayList<>();
        FakeStoreProductDTO[] response=restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDTO[].class);
        for(FakeStoreProductDTO fps:response){
            Product product=convertToProduct(fps);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product updateProductById(Long id, Product product) {
        FakeStoreProductDTO fakeStoreProductDTO=new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setImage(product.getImage());
        Category category= product.getCategory();
        fakeStoreProductDTO.setDescription(category.getDescription());
        RequestCallback requestCallback=restTemplate.httpEntityCallback(fakeStoreProductDTO, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor=
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class,restTemplate.getMessageConverters());
        FakeStoreProductDTO updateresponse=restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT,requestCallback,responseExtractor);
        return convertToProduct(updateresponse);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }
}
