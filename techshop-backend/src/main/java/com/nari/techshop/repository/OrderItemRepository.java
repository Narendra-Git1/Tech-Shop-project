package com.nari.techshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.techshop.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}