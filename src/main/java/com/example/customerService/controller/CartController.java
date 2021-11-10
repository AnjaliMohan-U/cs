package com.example.customerService.controller;

import com.example.customerService.model.CartModel;
import com.example.customerService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    //API - to add product to the cart
    @PatchMapping("/add-products-to-cart")
    public ResponseEntity<String> addProductToCart(@RequestParam String email, @RequestBody CartModel cartModel){
        return cartService.addProductToCart(email, cartModel);
    }

    //API - to view the cart details
    @GetMapping("/view-cart")
    public String viewCart(@RequestParam String email){
        return cartService.viewCart(email);
    }
}
