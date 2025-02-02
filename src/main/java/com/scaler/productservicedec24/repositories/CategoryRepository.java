package com.scaler.productservicedec24.repositories;

import com.scaler.productservicedec24.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    Optional<Category> findById(Long id);

    Category save(Category category);

    @Override
    void delete(Category category);

    Optional<Category> findByValue(String value);
}
