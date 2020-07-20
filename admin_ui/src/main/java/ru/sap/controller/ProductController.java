package ru.sap.controller;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sap.database.model.Product;
import ru.sap.dto.ProductDTO;
import ru.sap.service.ProductCategoryService;
import ru.sap.service.ProductService;


import javax.validation.Valid;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class ProductController {
    private ProductService productService;
    private ProductCategoryService productCategoryService;
    private ModelMapper modelMapper;

    public ProductController(ProductService productService,
                             ProductCategoryService productCategoryService,
                             ModelMapper modelMapper) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("newProduct")
    public String newProduct(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", productCategoryService.findAll());
        return "addProduct";
    }

    @PostMapping("addProduct")
    public String addProduct(@Valid ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "newProduct";
        }
        productService.save(productDTO);
        return "redirect:/newProduct";
    }

    @GetMapping("product")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("activePage", "product");
        return "product";
    }

    @GetMapping("editProduct")
    public String editProduct(@RequestParam(name = "id") Long id, Model model) {
        ProductDTO productDTO = new ProductDTO();
        modelMapper.map(productService.getProductById(id), productDTO);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("categories", productCategoryService.findAll());
        return "addProduct";
    }

    @DeleteMapping("deleteProduct")
    public String deleteProduct(@RequestParam(name = "id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/product";
    }
}
