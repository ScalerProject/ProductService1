package com.scaler.productservicedec24.models;

//import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity(name = "categories")
public class Category extends BaseModal {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
