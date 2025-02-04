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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("selfProductService")
//@Primary
public class SelfProductService implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found", id);
        }
        return optionalProduct.get();
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
