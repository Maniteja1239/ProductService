package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Entity
public class Product extends BaseModel{
    private String title;
    private Double price;
    private String image;
    @ManyToOne
    private Category category;
}
