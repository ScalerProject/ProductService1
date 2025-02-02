package com.scaler.productservicedec24.services;

import com.scaler.productservicedec24.exceptions.ProductNotFoundException;
import com.scaler.productservicedec24.models.Product;
import com.scaler.productservicedec24.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private final ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getSingleProduct(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found", id);
        }
        return optionalProduct.get();
    }

    public List<Product> getAllProducts(){
        return null;
    }
    public Product createProduct(Product product){
        return null;
    }
    public void deleteProduct(Long id){
        return;
    }
    public Product updateProduct(Long id, Product product){
        return null;
    }
    public Product replaceProduct(Long id, Product product){
        return null;
    }
}
