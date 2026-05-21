package com.nari.techshop.service;

import java.util.List;

import com.nari.techshop.entity.Order;

public interface OrderService {

    Order placeOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order updateOrder(Long id, Order order);

    void deleteOrder(Long id);

}