package com.ecommerce.products.controller;

import com.ecommerce.products.dao.InventoryEs;
import com.ecommerce.products.dao.InventoryMain;
import com.ecommerce.products.models.ProductsEs;
import com.ecommerce.products.models.ProductsMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<ProductsMain> productsMainList = inventoryMain.findAll();
        if (query != null) {
            List<ProductsMain> filteredProducts = new ArrayList<>();
            for (ProductsMain product : productsMainList) {
                if (product.getName().toLowerCase().contains(query.toLowerCase()) || product.getDescription().toLowerCase().contains(query.toLowerCase())){
                    filteredProducts.add(product);
                }
            }
            return ResponseEntity.ok(filteredProducts);
        }
        return ResponseEntity.ok(productsMainList);
    }

    @PostMapping("")
    public ResponseEntity<?> addProducts(@RequestBody List<ProductsEs> productsEsList) {
        List<ProductsMain> productsMainList = new ArrayList<>();
        for (ProductsEs product : productsEsList) {
            productsMainList.add(new ProductsMain(product.getId(), product.getName(), product.getDescription(), product.getPrice()));
        }
        inventoryMain.saveAll(productsMainList);
//        inventoryMain1.insertData(productsEsList);
        inventoryEs.saveAll(productsEsList);
        return ResponseEntity.ok(productsEsList);
    }

}
