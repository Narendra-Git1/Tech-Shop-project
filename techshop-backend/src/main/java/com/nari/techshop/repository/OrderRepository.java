package com.nari.techshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.techshop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}