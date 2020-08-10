package ru.sap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("index")
    public String getIndex() {
        return "index";
    }

    @GetMapping("main")
    public String getMain() {
        return "main";
    }

    @GetMapping("shoping-cart")
    public String getCart() {

        return "shoping-cart";
    }
}
