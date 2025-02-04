package com.scaler.productservicedec24.repositories;

import com.scaler.productservicedec24.models.Product;
import com.scaler.productservicedec24.repositories.projections.ProductWithTitleAndPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);

    @Override
    Page<Product> findAll(Pageable pageable);

    Product save(Product product);

    @Query("select p.title as title, p.price as price from products p where p.id = :id")
    ProductWithTitleAndPrice getProductTitleAndPrice(@Param("id") Long id);
}
