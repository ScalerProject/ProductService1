package com.scaler.productservicedec24.services;

import com.scaler.productservicedec24.exceptions.ProductNotFoundException;
import com.scaler.productservicedec24.models.Category;
import com.scaler.productservicedec24.models.Product;
import com.scaler.productservicedec24.repositories.CategoryRepository;
import com.scaler.productservicedec24.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service("selfProductService")
//@Primary
public class SelfProductService implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final RedisTemplate redisTemplate;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository, RedisTemplate redisTemplate) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getSingleProduct(Long id){
        // Try to get the product from Redis cache
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "product_" + id);
        if(product != null){
            return product;
        }
        // If not found in cache, fetch from DB
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found", id);
        }
        product = optionalProduct.get();
        // Store in Redis cache for future requests
        redisTemplate.opsForHash().put("PRODUCTS", "product_" + id, product);
        // Set TTL for the PRODUCTS hash (1 hour)
        redisTemplate.expire("PRODUCTS", Duration.ofHours(1));
        return product;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize){
        Page<Product> products = productRepository.findAll(
                PageRequest.of(pageNumber, pageSize, Sort.by("price").ascending())
        );
        if(products.isEmpty()){
            throw new ProductNotFoundException("No products found", null);
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        if (product.getCategory() == null) {
            throw new RuntimeException("Category cannot be null");
        }
        if (product.getCategory().getId() == null) {
            Category category = product.getCategory();
            String categoryValue = category.getValue();

            //since the category id is not present, it is a new category
            //and we should add it to the DB first

            //First we need to check if a category with the same value is
            //present in the DB
            Optional<Category> optionalCategory = categoryRepository.findByValue(categoryValue);

            if (optionalCategory.isEmpty()) {
                //create a new category in the DB
                categoryRepository.save(category);
            } else {
                product.setCategory(optionalCategory.get());
            }
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id){
        // Remove from DB
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found", id);
        }
        productRepository.delete(optionalProduct.get());
        // Remove from cache
        redisTemplate.opsForHash().delete("PRODUCTS", "product_" + id);
    }

    @Override
    public Product updateProduct(Long id, Product product){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found", id);
        }
        Product existingProduct = optionalProduct.get();
        // Update only non-null fields
        if(product.getTitle() != null) existingProduct.setTitle(product.getTitle());
        if(product.getDescription() != null) existingProduct.setDescription(product.getDescription());
        if(product.getPrice() != null) existingProduct.setPrice(product.getPrice());
        if(product.getImageUrl() != null) existingProduct.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) existingProduct.setCategory(product.getCategory());
        Product updatedProduct = productRepository.save(existingProduct);
        // Remove from cache so next get will refresh
        redisTemplate.opsForHash().delete("PRODUCTS", "product_" + id);
        return updatedProduct;
    }

    @Override
    public Product replaceProduct(Long id, Product product){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found", id);
        }
        Product existingProduct = optionalProduct.get();
        // Replace all fields
        existingProduct.setTitle(product.getTitle());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setCategory(product.getCategory());
        Product replacedProduct = productRepository.save(existingProduct);
        // Remove from cache so next get will refresh
        redisTemplate.opsForHash().delete("PRODUCTS", "product_" + id);
        return replacedProduct;
    }
}
