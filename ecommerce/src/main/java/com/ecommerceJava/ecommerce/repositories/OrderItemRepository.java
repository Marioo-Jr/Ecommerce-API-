package com.ecommerceJava.ecommerce.repositories;
import com.ecommerceJava.ecommerce.entities.OrderItem;
import com.ecommerceJava.ecommerce.entities.OrderItemPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

	
}

