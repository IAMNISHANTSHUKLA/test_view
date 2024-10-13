package com.ecommerce.orderManagement.controllers;

import com.ecommerce.orderManagement.dao.OrderCacheDao;
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

    @Autowired
    private OrderCacheDao orderCacheDao;

    @PostMapping("")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto order) {
        Order newOrder = new Order();
        newOrder.setProductId(order.getProductId());
        newOrder.setQuantity(order.getQuantity());
        newOrder.setStatus("CREATED");

//        Save the order to the database
        Order savedOrder = orderRepository.save(newOrder);
        OrderDto savedOrderDto = new OrderDto(savedOrder.getId(), savedOrder.getProductId(), savedOrder.getQuantity(), savedOrder.getStatus());

//        Save the order to the cache for tracking purposes with TTL of 10 seconds
        orderCacheDao.saveOrder("orders:" + savedOrder.getId(), savedOrderDto);

        return new ResponseEntity<>(savedOrderDto, null, 201);
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
//        Delete the order from the cache
        orderCacheDao.deleteOrder("orders:" + id);

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
