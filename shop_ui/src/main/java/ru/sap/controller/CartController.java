package ru.sap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sap.service.CartData;
import ru.sap.service.CartService;
import ru.sap.service.LineItem;
import ru.sap.shop_common.dto.ProductDTO;
import ru.sap.shop_common.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/shop-cart")
public class CartController {
    private CartService cartService;
    private ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

//    @GetMapping
//    public String getCart(Model model) {
//        model.addAttribute("lineItems", cartService.getLineItems());
//        model.addAttribute("total", cartService.getTotal());
//        return "shop-cart";
//    }

    @GetMapping
    public String getCart(Model model) {
        List<LineItem> lineItems = cartService.getLineItems();
        CartData cartData = new CartData(lineItems);
        model.addAttribute("cartData", cartData);
        model.addAttribute("total", cartService.getTotal());
        return "shop-cart";
    }

    @GetMapping("add")
    public String addProductToCart(@RequestParam(name = "id") Long id,Model model) {
        ProductDTO productDTO = productService.getProductById(id);
        cartService.addLineItem(productDTO);
        return "redirect:/shop-cart";
    }

//    @PostMapping("setQty")
//    public String setQty(@RequestParam Integer id, @RequestParam Integer qty) {
//
//        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//        cartService.setQty(id, qty);
//        return "redirect:/shop-cart";
//    }

    @PostMapping("setQty")
    public String setQty(CartData cartData) {
        for (Integer i : cartData.getQtys())
            System.out.println(i + "$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        cartService.setQty(cartData.getQtys());
        return "redirect:/shop-cart";
    }

    @GetMapping("delete")
    public String deleteProductFromCart(@RequestParam(name = "id") Integer id, Model model) {
        cartService.removeLineItem(id);
        return "redirect:/shop-cart";
    }
}
