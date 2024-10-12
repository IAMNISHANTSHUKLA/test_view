package com.ecommerce.products.dao;

import com.ecommerce.products.models.ProductsEs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryEs extends MongoRepository<ProductsEs, Integer> {
}
