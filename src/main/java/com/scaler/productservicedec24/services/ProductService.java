package com.scaler.productservicedec24.services;

import com.scaler.productservicedec24.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(Long id);
    public Page<Product> getAllProducts(int pageNumber, int pageSize);
    public Product createProduct(Product product);
    public void deleteProduct(Long id);
    public Product updateProduct(Long id, Product product);
    public Product replaceProduct(Long id, Product product);
}
