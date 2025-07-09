package com.spring.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.demo.model.CartItem;
import com.spring.demo.model.User;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
}