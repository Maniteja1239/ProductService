package com.example.productservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Entity
public class Category extends BaseModel{
    private String description;
}
