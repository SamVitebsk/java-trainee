package com.andersen.internetShop.service;

import com.andersen.internetShop.dao.OrderRepository;
import com.andersen.internetShop.dao.User;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public boolean makeOrder(User user, BigDecimal total, Boolean accepted) {
        return orderRepository.create(user.getId(), total, accepted);
    }
}
