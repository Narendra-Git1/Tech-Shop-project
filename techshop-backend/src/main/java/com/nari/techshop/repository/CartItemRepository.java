package com.nari.techshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.techshop.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}