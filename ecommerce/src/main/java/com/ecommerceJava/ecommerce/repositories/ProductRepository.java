package com.ecommerceJava.ecommerce.repositories;

import com.ecommerceJava.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {



}
