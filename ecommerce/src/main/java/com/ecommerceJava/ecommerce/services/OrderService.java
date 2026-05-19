package com.ecommerceJava.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecommerceJava.ecommerce.dto.OrderDTO;
import com.ecommerceJava.ecommerce.entities.Order;
import com.ecommerceJava.ecommerce.repositories.OrderRepository;
import com.ecommerceJava.ecommerce.services.exeptions.ResourceNotFoundException;



@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(long id) {
        Order order = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("recurso nao encontrado"));
        return new OrderDTO(order); 
    }

}
