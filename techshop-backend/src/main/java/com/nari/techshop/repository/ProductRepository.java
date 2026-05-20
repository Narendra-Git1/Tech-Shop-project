package com.nari.techshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.techshop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}