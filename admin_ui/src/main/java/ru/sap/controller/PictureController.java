package ru.sap.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sap.service.ProductService;

@Controller
public class PictureController {
    private ProductService productService;

    public PictureController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product")
    public String product() {

        return "product";
    }
}
