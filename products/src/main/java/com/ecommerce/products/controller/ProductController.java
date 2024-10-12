package com.ecommerce.products.controller;

import com.ecommerce.products.dao.InventoryEs;
import com.ecommerce.products.dao.InventoryMain;
import com.ecommerce.products.models.ProductsEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private InventoryEs inventoryEs;

    @Autowired
    private InventoryMain inventoryMain;

    @GetMapping("")
    public ResponseEntity<?> getProducts(@RequestParam(required = false, name = "q") String query) {
        if (query != null) {
            return ResponseEntity.ok(inventoryEs.findAll().stream()
                    .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()) || product.getDescription().toLowerCase().contains(query.toLowerCase()))
                    .toArray());
        }
        return ResponseEntity.ok(inventoryEs.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> addProducts(@RequestBody List<ProductsEs> productsEsList) {
        inventoryMain.insertData(productsEsList);
        inventoryEs.saveAll(productsEsList);
        return ResponseEntity.ok(productsEsList);
    }

}
