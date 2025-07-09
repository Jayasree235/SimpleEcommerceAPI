package com.spring.demo.service;

import com.spring.demo.model.*;
import com.spring.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    @Autowired
    private CartRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    public List<CartItem> addItem(User user, Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow();
        for (CartItem item : cartItemRepository.findByUser(user)) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                cartItemRepository.save(item);
                return cartItemRepository.findByUser(user);
            }
        }
        CartItem newItem = new CartItem(product, quantity, user);
        cartItemRepository.save(newItem);
        return cartItemRepository.findByUser(user);
    }

    public List<CartItem> updateItem(User user, Long itemId, int quantity) {
        cartItemRepository.findById(itemId)
            .filter(i -> i.getUser().equals(user))
            .ifPresent(i -> {
                i.setQuantity(quantity);
                cartItemRepository.save(i);
            });
        return cartItemRepository.findByUser(user);
    }

    public List<CartItem> removeItem(User user, Long itemId) {
        cartItemRepository.findById(itemId)
            .filter(i -> i.getUser().equals(user))
            .ifPresent(cartItemRepository::delete);
        return cartItemRepository.findByUser(user);
    }
}