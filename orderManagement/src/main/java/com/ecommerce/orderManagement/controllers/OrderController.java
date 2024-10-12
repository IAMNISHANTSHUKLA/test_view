package com.ecommerce.orderManagement.controllers;

import com.ecommerce.orderManagement.dao.OrderRepository;
import com.ecommerce.orderManagement.models.Order;
import com.ecommerce.orderManagement.models.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto order) {
        Order newOrder = new Order();
        newOrder.setProductId(order.getProductId());
        newOrder.setQuantity(order.getQuantity());
        newOrder.setStatus("CREATED");

        Order savedOrder = orderRepository.save(newOrder);

        return new ResponseEntity<>(new OrderDto(savedOrder.getId(), savedOrder.getProductId(), savedOrder.getQuantity(), savedOrder.getStatus()), null, 201);
    }

    @PutMapping("/pay/{id}")
    public ResponseEntity<String> payOrder(@PathVariable int id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return new ResponseEntity<>("Order not found", null, 404);
        }
        if (!order.getStatus().equals("CREATED")) {
            return new ResponseEntity<>("Session expired. Please try again.", null, 400);
        }
        order.setStatus("SUCCESS");
        orderRepository.save(order);
        return new ResponseEntity<>("Order paid", null, 200);
    }

    @GetMapping("/checkStatus/{id}")
    public ResponseEntity<String> checkOrderStatus(@PathVariable int id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return new ResponseEntity<>("Order not found", null, 404);
        }
        return new ResponseEntity<>(order.getStatus(), null, 200);
    }
}
