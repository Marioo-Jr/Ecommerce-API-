package com.ecommerceJava.ecommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecommerceJava.ecommerce.dto.OrderDTO;
import com.ecommerceJava.ecommerce.dto.OrderItemDTO;
import com.ecommerceJava.ecommerce.entities.Order;
import com.ecommerceJava.ecommerce.entities.OrderItem;
import com.ecommerceJava.ecommerce.entities.OrderStatus;
import com.ecommerceJava.ecommerce.entities.Product;
import com.ecommerceJava.ecommerce.entities.User;
import com.ecommerceJava.ecommerce.repositories.OrderItemRepository;
import com.ecommerceJava.ecommerce.repositories.OrderRepository;
import com.ecommerceJava.ecommerce.repositories.ProductRepository;
import com.ecommerceJava.ecommerce.services.exeptions.ResourceNotFoundException;

import jakarta.validation.Valid;



@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(long id) {
        Order order = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("recurso nao encontrado"));
        
            authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order); 
    }

    @Transactional
    public @Valid OrderDTO insert(OrderDTO dto) {

        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        
        User user  = userService.authenticated();
        order.setClient(user);

        for (OrderItemDTO itemDTO : dto.getItems()){
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item =  new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());

            order.getItems().add(item);
        }

        repository.save(order);
        orderItemRepository.saveAll(order.getItems());
        return new OrderDTO(order);
    }
    

}
