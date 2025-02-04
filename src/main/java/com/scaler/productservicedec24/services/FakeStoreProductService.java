package com.scaler.productservicedec24.services;

import com.scaler.productservicedec24.dtos.FakeStoreProductDto;
import com.scaler.productservicedec24.exceptions.ProductNotFoundException;
import com.scaler.productservicedec24.models.Category;
import com.scaler.productservicedec24.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
@Primary
public class FakeStoreProductService implements ProductService{
    RedisTemplate redisTemplate;
    RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate){
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId){
        //try to get the product from redis
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "product_"+productId);

        //if product is found in redis, return it
        if(product != null){
            return product;
        }
        //if product is not found in redis, fetch it from the API
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);
        if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Something went wrong", productId);
        }
        product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

        //store the product in redis
        redisTemplate.opsForHash().put("PRODUCTS", "product_"+productId, product);

        return product;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize){
////      We can't use this because of type erasures of Generics
//        List<FakeStoreProductDto> fakeStoreProductDtos = restTemplate
//                .getForObject("https://fakestoreapi.com/products", List<FakeStoreProductDto>.class);

        FakeStoreProductDto[] fakeStoreProductDtos =
                restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return new PageImpl<>(products);
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

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setId(fakeStoreProductDto.getId());
        product.setImageUrl(fakeStoreProductDto.getImage());

        product.setCategory(new Category());
        product.getCategory().setValue(fakeStoreProductDto.getCategory());

        return product;
    }
}
