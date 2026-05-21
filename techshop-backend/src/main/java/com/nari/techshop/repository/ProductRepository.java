package com.nari.techshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.techshop.entity.Product;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(
            String keyword
    );

    List<Product> findByCategoryId(
            Long categoryId
    );

}