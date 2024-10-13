package com.ecommerce.products.dao;

import com.ecommerce.products.models.ProductsMain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryMain extends JpaRepository<ProductsMain, Integer> {
}
