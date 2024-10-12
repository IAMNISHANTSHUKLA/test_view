package com.ecommerce.orderManagement.dao;

import com.ecommerce.orderManagement.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
