package com.spring.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.demo.model.CartItem;
import com.spring.demo.model.Order;
import com.spring.demo.model.OrderItem;
import com.spring.demo.model.User;
import com.spring.demo.repository.CartRepository;
import com.spring.demo.repository.OrderRepository;
import com.spring.demo.repository.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    public Order placeOrder(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        List<CartItem> cartItems = cartRepository.findByUser(user);

        double total = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        Order order = new Order(user, 0.0, new Date());
        orderRepository.save(order);

        for (CartItem cartItem : cartItems) {
            double itemTotal = cartItem.getProduct().getPrice() * cartItem.getQuantity();
            total += itemTotal;
            OrderItem orderItem = new OrderItem(order, cartItem.getProduct(), cartItem.getQuantity(), cartItem.getProduct().getPrice());
            orderItems.add(orderItem);
        }

        order.setTotalAmount(total);
        order.setItems(orderItems);
        orderRepository.save(order);
        cartRepository.deleteByUser(user);

        return order;
    }

    public List<Order> getOrders(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return orderRepository.findByUser(user);
    }
}