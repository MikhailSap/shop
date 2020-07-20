package ru.sap.controller;


import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sap.database.model.ProductCategory;
import ru.sap.service.ProductCategoryServiceImpl;

import java.util.Optional;

@Controller
public class ProductCategoryController {
    private ProductCategoryServiceImpl productCategoryService;

    public ProductCategoryController(ProductCategoryServiceImpl productCategoryService) {
        this.productCategoryService = productCategoryService;
    }


    @GetMapping("newCategory")
    private String newCategory(Model model) {
        model.addAttribute("category", new ProductCategory());
        return "addCategory";
    }

    @PostMapping("addCategory")
    private String addCategory(ProductCategory productCategory) {
        productCategoryService.save(productCategory);
        return "redirect:/newCategory";
    }

    @GetMapping("category")
    public String getCategories(Model model) {
        model.addAttribute("categories", productCategoryService.findAll());
        model.addAttribute("activePage", "category");
        return "category";
    }

    @GetMapping("editCategory")
    public String editCategory(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("category", productCategoryService.getCategoryById(id));
        return "addCategory";
    }

    @DeleteMapping("deleteCategory")
    public String deleteCategory(@RequestParam(name = "id") Long id) {
        productCategoryService.deleteCategoryById(id);
        return "redirect:/category";
    }
}
