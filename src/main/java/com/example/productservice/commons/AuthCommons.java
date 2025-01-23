package com.example.productservice.commons;

import com.example.productservice.DTOs.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    public UserDTO validate(String token){
        return restTemplate.getForEntity("http://localhost:8080/users/validate/"+token, UserDTO.class).getBody();
    }
}
