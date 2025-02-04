//package com.scaler.productservicedec24;
//
//import com.scaler.productservicedec24.repositories.ProductRepository;
//import com.scaler.productservicedec24.repositories.projections.ProductWithTitleAndPrice;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class ProductServiceDec24ApplicationTests {
//    @Autowired
//    ProductRepository productRepository;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void testQuery(){
//        ProductWithTitleAndPrice productWithTitleAndPrice =
//        productRepository.getProductTitleAndPrice(2L);
//        System.out.println(productWithTitleAndPrice.getTitle());
//        System.out.println(productWithTitleAndPrice.getPrice());
//    }
//}
