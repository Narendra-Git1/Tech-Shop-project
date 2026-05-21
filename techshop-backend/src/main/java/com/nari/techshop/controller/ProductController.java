package com.nari.techshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nari.techshop.dto.ProductRequestDTO;
import com.nari.techshop.dto.ProductResponseDTO;
import com.nari.techshop.entity.Category;
import com.nari.techshop.entity.Product;
import com.nari.techshop.service.CategoryService;
import com.nari.techshop.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ProductResponseDTO createProduct(
            @RequestBody ProductRequestDTO dto) {

        Category category =
                categoryService.getCategoryById(
                        dto.getCategoryId()
                );

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setImageUrl(dto.getImageUrl());

        // IMPORTANT
        product.setCategory(category);

        Product savedProduct =
                productService.createProduct(product);

        return new ProductResponseDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getStockQuantity(),
                savedProduct.getImageUrl(),

                // IMPORTANT FIX
                category.getName()
        );
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {

        return productService.getAllProducts()
                .stream()
                .map(product ->
                        new ProductResponseDTO(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice(),
                                product.getStockQuantity(),
                                product.getImageUrl(),

                                // NULL SAFE
                                product.getCategory() != null
                                        ? product.getCategory().getName()
                                        : null
                        )
                )
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(
            @PathVariable Long id) {

        Product product =
                productService.getProductById(id);

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getImageUrl(),

                // NULL SAFE
                product.getCategory() != null
                        ? product.getCategory().getName()
                        : null
        );
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO dto) {

        Category category =
                categoryService.getCategoryById(
                        dto.getCategoryId()
                );

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setImageUrl(dto.getImageUrl());

        // IMPORTANT
        product.setCategory(category);

        Product updatedProduct =
                productService.updateProduct(id, product);

        return new ProductResponseDTO(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getDescription(),
                updatedProduct.getPrice(),
                updatedProduct.getStockQuantity(),
                updatedProduct.getImageUrl(),

                // IMPORTANT FIX
                category.getName()
        );
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return "Product Deleted Successfully";
    }
    
    @GetMapping("/search")
    public List<ProductResponseDTO> searchProducts(
            @RequestParam String keyword) {

        return productService.searchProducts(keyword)
                .stream()
                .map(product ->
                        new ProductResponseDTO(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice(),
                                product.getStockQuantity(),
                                product.getImageUrl(),

                                product.getCategory() != null
                                        ? product.getCategory().getName()
                                        : null
                        )
                )
                .collect(Collectors.toList());
    }
    
    @GetMapping("/filter")
    public List<ProductResponseDTO> filterProductsByCategory(
            @RequestParam Long categoryId) {

        return productService
                .filterProductsByCategory(categoryId)
                .stream()
                .map(product ->
                        new ProductResponseDTO(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice(),
                                product.getStockQuantity(),
                                product.getImageUrl(),

                                product.getCategory() != null
                                        ? product.getCategory().getName()
                                        : null
                        )
                )
                .collect(Collectors.toList());
    }

}