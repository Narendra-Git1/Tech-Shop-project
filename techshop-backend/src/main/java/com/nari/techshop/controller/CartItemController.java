package com.nari.techshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nari.techshop.entity.CartItem;
import com.nari.techshop.service.CartItemService;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody CartItem cartItem) {
        return cartItemService.addToCart(cartItem);
    }

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public CartItem getCartItemById(@PathVariable Long id) {
        return cartItemService.getCartItemById(id);
    }

    @PutMapping("/update/{id}")
    public CartItem updateCartItem(@PathVariable Long id,
                                   @RequestBody CartItem cartItem) {

        return cartItemService.updateCartItem(id, cartItem);
    }

    @DeleteMapping("/remove/{id}")
    public String removeCartItem(@PathVariable Long id) {

        cartItemService.removeCartItem(id);

        return "Cart Item Removed Successfully";
    }

}