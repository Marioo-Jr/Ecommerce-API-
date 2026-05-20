package com.ecommerceJava.ecommerce.controllers;
import com.ecommerceJava.ecommerce.dto.CategoryDTO;
import com.ecommerceJava.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/categories")
public class CategoryController {


    @Autowired
    private CategoryService service;
    

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findALL () {
        List<CategoryDTO> list =  service.findALL();
        return ResponseEntity.ok(list);
    }




}
