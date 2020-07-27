package ru.sap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sap.service.CartService;
import ru.sap.shop_common.dto.ProductDTO;
import ru.sap.shop_common.service.ProductService;

@Controller
@RequestMapping("/shop-cart")
public class CartController {
    private CartService cartService;
    private ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String getCart(Model model) {
        model.addAttribute("lineItems", cartService.getLineItems());
        return "shop-cart";
    }

    @GetMapping("add")
    public String addProductToCart(@RequestParam(name = "id") Long id,Model model) {
        ProductDTO productDTO = productService.getProductById(id);
        cartService.addLineItem(productDTO);
        model.addAttribute("lineItems", cartService.getLineItems());
        return "redirect:/shop-cart";
    }

    @PostMapping("setQty")
    public String setQty(@RequestParam Integer id, @RequestParam Integer qty) {
        cartService.setQty(id, qty);
        return "redirect:/shop-cart";
    }

    @GetMapping("delete")
    public String deleteProductFromCart(@RequestParam(name = "id") Integer id, Model model) {
        cartService.removeLineItem(id);
        return "redirect:/shop-cart";
    }
}
