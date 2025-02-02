package com.scaler.productservicedec24.models;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//@MappedSuperclass
public class BaseModal {
//    @Id
    private Long id;
    private Date createdAt;
    private Date updatedAt;

    public void setId(Long id) {
        this.id = id;
    }
}
