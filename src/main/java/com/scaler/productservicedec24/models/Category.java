package com.scaler.productservicedec24.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "categories")
public class Category extends BaseModal {
    //This is to make sure the value is unique and no duplicate entries are made
    //for the same category
    @Column(unique = true)
    private String value;

    /// Doing this tells JPA that we already have a mapping for this
    /// and don't need to create. We don't need it now but there may be a
    /// use case where we need a list like this in the future
    /// We would also have to use fetch type eager because the default
    /// behavior for fetching objects in the part of the list is lazy
    /// where the internal details of the list are not fetched until they are
    ///
//    @OneToMany(
//      mappedBy = "category",
//      cascade= {CascadeType.PERSIST, CascadeType.REMOVE},
//      fetch = FetchType.EAGER
//     )
//    private List<Product> products;

    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
