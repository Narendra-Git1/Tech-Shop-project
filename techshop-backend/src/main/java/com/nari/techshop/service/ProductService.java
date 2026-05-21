package com.nari.techshop.service;

import java.util.List;

import com.nari.techshop.entity.Product;

public interface ProductService {

    Product createProduct(Product product);
    List<Product> searchProducts(String keyword);
    
    List<Product> filterProductsByCategory(
            Long categoryId
    );

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

}