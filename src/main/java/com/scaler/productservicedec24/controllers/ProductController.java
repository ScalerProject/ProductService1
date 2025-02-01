package com.scaler.productservicedec24.controllers;

import com.scaler.productservicedec24.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long productId) {
        return new Product();
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return null;
    }

    @PostMapping
    public Product createProduct() {
        return new Product();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {
        return;
    }

    @PatchMapping("/{id}")
    public void updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) {
        return ;
    }

    @PutMapping("/{id}")
    public void replaceProduct(@PathVariable("id") Long productId, @RequestBody Product product) {
        return ;
    }

}
