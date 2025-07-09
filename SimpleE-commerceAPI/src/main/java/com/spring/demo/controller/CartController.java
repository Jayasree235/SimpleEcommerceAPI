package com.spring.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.model.CartItem;
import com.spring.demo.model.User;
import com.spring.demo.service.CartService;
import com.spring.demo.util.CurrentUserUtil;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public List<CartItem> viewCart() {
        User user = CurrentUserUtil.getCurrentUser();
        return cartService.getCartItems(user);
    }

    @PostMapping("/add")
    public List<CartItem> addItem(@RequestParam Long productId, @RequestParam int quantity) {
        User user = CurrentUserUtil.getCurrentUser();
        return cartService.addItem(user, productId, quantity);
    }

    @PutMapping("/update")
    public List<CartItem> updateItem(@RequestParam Long itemId, @RequestParam int quantity) {
        User user = CurrentUserUtil.getCurrentUser();
        return cartService.updateItem(user, itemId, quantity);
    }

    @DeleteMapping("/remove")
    public List<CartItem> removeItem(@RequestParam Long itemId) {
        User user = CurrentUserUtil.getCurrentUser();
        return cartService.removeItem(user, itemId);
    }
}