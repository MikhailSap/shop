package ru.sap.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("categories")
    public String filter(@RequestParam(value = "partOfName", required = false) String partOfName,
                         @RequestParam(value = "pageNumber") Optional<Integer> pageNumber,
                         @RequestParam(value = "pageSize") Optional<Integer> pageSize,
                         Model model) {
        model.addAttribute("productsPage",
                productCategoryService.filter(
                        partOfName,
                        PageRequest.of(pageNumber.orElse(1) -1, pageSize.orElse(5)))
        );
        model.addAttribute("partOfName", partOfName);
        return "categories";
    }
}
