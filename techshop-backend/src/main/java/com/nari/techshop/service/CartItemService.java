package com.nari.techshop.service;

import java.util.List;

import com.nari.techshop.entity.CartItem;

public interface CartItemService {

    CartItem addToCart(CartItem cartItem);

    List<CartItem> getAllCartItems();

    CartItem getCartItemById(Long id);

    CartItem updateCartItem(Long id, CartItem cartItem);

    void removeCartItem(Long id);

}