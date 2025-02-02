package com.scaler.productservicedec24.services;

import com.scaler.productservicedec24.models.Product;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(Long id);
    public List<Product> getAllProducts();
    public Product createProduct(Product product);
    public void deleteProduct(Long id);
    public Product updateProduct(Long id, Product product);
    public Product replaceProduct(Long id, Product product);
}
