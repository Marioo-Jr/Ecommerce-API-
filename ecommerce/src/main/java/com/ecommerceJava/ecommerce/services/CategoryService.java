package com.ecommerceJava.ecommerce.services;

import com.ecommerceJava.ecommerce.dto.CategoryDTO;
import com.ecommerceJava.ecommerce.entities.Category;
import com.ecommerceJava.ecommerce.repositories.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;


   

    @Transactional(readOnly = true)
    public List<CategoryDTO> findALL() {
        List<Category> result = repository.findAll();
        return result.stream().map(x -> new CategoryDTO(x)).toList();


    }


   

}
