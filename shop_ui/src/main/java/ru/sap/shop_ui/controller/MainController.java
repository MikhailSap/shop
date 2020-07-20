package ru.sap.shop_ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sap.database.repo.ProductRepo;
import ru.sap.shop_ui.service.Service;

@Controller
public class MainController {

    private Service service;

    public MainController(Service service) {
        this.service = service;
    }

    @GetMapping("index")
    public String getIndex() {
        return "index";
    }

    @GetMapping("main")
    public String getMain() {
        return "main";
    }

    @GetMapping("shop-details")
    public String getDetails(@RequestParam(name = "id") Long id, Model model) {
        service.getProduct(id);
        model.addAttribute("product", service.getProduct(id));
        return "shop-details";
    }

    @GetMapping("shop-grid")
    public String getGrid(Model model) {
        model.addAttribute("products", service.findAll());
        return "shop-grid";
    }

    @GetMapping("shoping-cart")
    public String getCart() {

        return "shoping-cart";
    }
}
