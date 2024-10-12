package com.ecommerce.products.dao;

import com.ecommerce.products.models.ProductsEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryMain {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertData(List<ProductsEs> productsEsList){
        for (ProductsEs product : productsEsList) {
            jdbcTemplate.update("INSERT INTO products (id, name, description, price) VALUES (?, ?, ?, ?)",
                    product.getId(), product.getName(), product.getDescription(), product.getPrice());
        }
    }
}
