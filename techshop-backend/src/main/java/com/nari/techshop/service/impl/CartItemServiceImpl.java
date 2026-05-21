package com.nari.techshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nari.techshop.entity.CartItem;
import com.nari.techshop.repository.CartItemRepository;
import com.nari.techshop.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem addToCart(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public CartItem updateCartItem(Long id, CartItem cartItem) {

        CartItem existingCartItem =
                cartItemRepository.findById(id).orElse(null);

        if (existingCartItem != null) {

            existingCartItem.setQuantity(cartItem.getQuantity());
            existingCartItem.setUser(cartItem.getUser());
            existingCartItem.setProduct(cartItem.getProduct());

            return cartItemRepository.save(existingCartItem);
        }

        return null;
    }

    @Override
    public void removeCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

}