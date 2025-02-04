package com.scaler.productservicedec24.controllers;

import com.scaler.productservicedec24.models.Product;
import com.scaler.productservicedec24.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long productId) {
        return new ResponseEntity<>(
                productService.getSingleProduct(productId),
                HttpStatus.OK
        );
////IMPORTANT: We should not use try-catch block in controller and instead handle this using the controller advices
//// which can handle multiple exceptions
//        try {
//            return new ResponseEntity<>(
//                    productService.getSingleProduct(productId),
//                    HttpStatus.OK
//            );
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(
//                    HttpStatus.BAD_REQUEST
//            );
        /// Since ProductNotFoundException is itself a runtime exception, we should put it before the Runtime Exception
        /// to catch it first. The hierarchy of exceptions is checked from top to bottom
//        } catch (ProductNotFoundException e) {
//            return new ResponseEntity<>(
//                    HttpStatus.BAD_REQUEST
//            );
//        }
        //Now we can alter the status code based on exceptions and send it to the client
    }

    @GetMapping()
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(
                productService.getAllProducts(pageNumber, pageSize),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(
                productService.createProduct(product),
                HttpStatus.OK
        );
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
