package ru.sap.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sap.database.model.Product;
import ru.sap.service.ProductCategoryService;
import ru.sap.service.ProductService;


import javax.validation.Valid;
import java.util.Optional;


@Controller
public class ProductController {
    private ProductService productService;
    private ProductCategoryService productCategoryService;

    public ProductController(ProductService productService, ProductCategoryService productCategoryService) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("newProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productCategoryService.findAll());
        return "addProduct";
    }

    @PostMapping("addProduct")
    public String addProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "newProduct";
        }
        productService.save(product);
        return "redirect:/newProduct";
    }

    @GetMapping("products")
    public String filter(@RequestParam(value = "minPrice", required = false) Integer min,
                         @RequestParam(value = "maxPrice", required = false) Integer max,
                         @RequestParam(value = "partOfName", required = false) String partOfName,
                         @RequestParam(value = "pageNumber") Optional<Integer> pageNumber,
                         @RequestParam(value = "pageSize") Optional<Integer> pageSize,
                         Model model) {
        model.addAttribute("productsPage",
                productService.filter(
                        min, max,
                        partOfName,
                        PageRequest.of(pageNumber.orElse(1) -1, pageSize.orElse(5)))
        );
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("partOfName", partOfName);
        return "products";
    }

    @GetMapping("edit")
    public String editProduct(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product";
    }

    @DeleteMapping("delete")
    public String deleteProduct(@RequestParam(name = "id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
}
