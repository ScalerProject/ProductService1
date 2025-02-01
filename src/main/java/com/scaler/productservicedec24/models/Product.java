package com.scaler.productservicedec24.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModal{
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;
}
