package ru.sap.shop_common.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sap.shop_common.dto.ProductDTO;
import ru.sap.shop_common.service.ProductCategoryService;
import ru.sap.shop_common.service.ProductService;


import javax.validation.Valid;


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

    @GetMapping("getProduct")
    public String getProductById(@RequestParam(name = "id") Long id, Model model) {
        System.out.println("im here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        model.addAttribute("productDTO", productService.getProductById(id));
        return "product-details";
    }

    @GetMapping("product")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("activePage", "product");
        return "product";
    }

    @GetMapping("shop")
    public String getList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "shop";
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
