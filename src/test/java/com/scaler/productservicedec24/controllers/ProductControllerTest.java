package com.scaler.productservicedec24.controllers;

import com.scaler.productservicedec24.models.Product;
import com.scaler.productservicedec24.services.ProductService;
import com.scaler.productservicedec24.services.ProductServiceStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void getAllProducts() {
        //Arrange
        Product p1 = new Product();
        p1.setId(1L);
        p1.setTitle("Product 1");
        p1.setPrice(100.0);
        p1.setDescription(null);

        Product p2 = new Product();
        p2.setId(2L);
        p2.setTitle("Product 2");
        p2.setPrice(200.0);
        p2.setDescription(null);

        List<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add(p1);
        expectedProductList.add(p2);

        //Act
        when(productService.getAllProducts()).thenReturn(expectedProductList);
        List<Product> actualProductList = productController.getAllProducts().getBody();

        //Assert
        assertEquals(expectedProductList.size(), actualProductList.size());

        for(int i=0; i<expectedProductList.size(); i++){
            assertEquals(expectedProductList.get(i), actualProductList.get(i));
        }
    }

    @Test
    void getSingleProduct() {
        //Arrange
        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setTitle("Product 1");
        expectedProduct.setPrice(100.0);
        expectedProduct.setDescription(null);

        //Act
        when(productService.getSingleProduct(1L)).thenReturn(expectedProduct);
        Product actualProduct = productController.getSingleProduct(1L).getBody();

        //Assert
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void createProduct() {
        ProductServiceStub productServiceStub = new ProductServiceStub();
        productServiceStub.createProduct(new Product());
        productServiceStub.createProduct(new Product());
        productServiceStub.createProduct(new Product());
        assertEquals(3, productServiceStub.getCount());
    }
}