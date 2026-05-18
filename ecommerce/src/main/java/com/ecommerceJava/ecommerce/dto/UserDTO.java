package com.ecommerceJava.ecommerce.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import com.ecommerceJava.ecommerce.entities.User;


public class UserDTO {


    private long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birth_date;

    private List<String> roles = new ArrayList<>();

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        birth_date = entity.getBirth_date();
        for(GrantedAuthority role : entity.getRoles()){
            roles.add(role.getAuthority());
        }

        
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public List<String> getRoles() {
        return roles;
    }


}
