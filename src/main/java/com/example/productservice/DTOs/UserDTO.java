package com.example.productservice.DTOs;

import com.example.productservice.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private List<Role> roles;

/*
    public static UserDTO from(User user){
        UserDTO dto=new UserDTO();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        return dto;
    }

 */
}
