package com.ecommerceJava.ecommerce.repositories;
import com.ecommerceJava.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
