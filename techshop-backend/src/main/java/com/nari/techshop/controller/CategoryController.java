package com.nari.techshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nari.techshop.dto.CategoryRequestDTO;
import com.nari.techshop.dto.CategoryResponseDTO;
import com.nari.techshop.entity.Category;
import com.nari.techshop.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponseDTO createCategory(
            @RequestBody CategoryRequestDTO dto) {

        Category category = new Category();

        category.setName(dto.getName());

        Category savedCategory =
                categoryService.createCategory(category);

        return new CategoryResponseDTO(
                savedCategory.getId(),
                savedCategory.getName()
        );
    }

    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {

        return categoryService.getAllCategories()
                .stream()
                .map(category ->
                        new CategoryResponseDTO(
                                category.getId(),
                                category.getName()
                        )
                )
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getCategoryById(
            @PathVariable Long id) {

        Category category =
                categoryService.getCategoryById(id);

        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO dto) {

        Category category = new Category();

        category.setName(dto.getName());

        Category updatedCategory =
                categoryService.updateCategory(id, category);

        return new CategoryResponseDTO(
                updatedCategory.getId(),
                updatedCategory.getName()
        );
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        return "Category Deleted Successfully";
    }

}