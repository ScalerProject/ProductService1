package com.scaler.productservicedec24.services;

import com.scaler.productservicedec24.dtos.FakeStoreProductDto;
import com.scaler.productservicedec24.models.Category;
import com.scaler.productservicedec24.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    @Override
    public List<Product> getAllProducts(){
        return null;
    }

    public Product getSingleProduct(Long productId){
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
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
