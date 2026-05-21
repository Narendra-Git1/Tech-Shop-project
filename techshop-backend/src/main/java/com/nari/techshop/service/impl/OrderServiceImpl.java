package com.nari.techshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nari.techshop.entity.Order;
import com.nari.techshop.repository.OrderRepository;
import com.nari.techshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order updateOrder(Long id, Order order) {

        Order existingOrder =
                orderRepository.findById(id).orElse(null);

        if (existingOrder != null) {

            existingOrder.setOrderDate(order.getOrderDate());
            existingOrder.setTotalAmount(order.getTotalAmount());
            existingOrder.setStatus(order.getStatus());
            existingOrder.setUser(order.getUser());

            return orderRepository.save(existingOrder);
        }

        return null;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}