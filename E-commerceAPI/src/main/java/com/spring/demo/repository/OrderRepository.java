package com.spring.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.demo.model.Order;
import com.spring.demo.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}