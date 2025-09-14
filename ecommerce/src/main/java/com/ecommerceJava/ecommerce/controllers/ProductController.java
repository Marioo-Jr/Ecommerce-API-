package com.ecommerceJava.ecommerce.controllers;
import com.ecommerceJava.ecommerce.dto.ProductDTO;
import com.ecommerceJava.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {


    @Autowired
    private ProductService service;


    //foi usado o ResponseEntity para deixar o codigo com boas praticas no retorno do header
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
            ProductDTO dto = service.findById(id);
            return ResponseEntity.ok(dto); // resposta 200
    }
    //public ProductDTO findById(@PathVariable long id) {
    //ProductDTO dto = service.findById(id);
    //return dto;
    //}


    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findALL (Pageable pageable) {
        Page<ProductDTO> dto =  service.findALL(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri(); // link do recurso que vai ser criado no header da resposta
        return ResponseEntity.created(uri).body(dto); // created codigo 201
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update (@PathVariable Long id,@Valid @RequestBody ProductDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> update (@PathVariable Long id) {
        service.delete(id);// resposta certa sem corpo = 204
        return ResponseEntity.noContent().build();
    }


}
