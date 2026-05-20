package com.nari.techshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.techshop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}