package com.andersen.internetShop.service;

import com.andersen.internetShop.dao.Order;
import com.andersen.internetShop.dao.OrderRepository;
import com.andersen.internetShop.dao.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public boolean makeOrder(User user, BigDecimal total, Boolean accepted) {
        return orderRepository.create(user.getId(), total, accepted);
    }

    public boolean acceptOrder(User user, Integer orderId) {
        return orderRepository.acceptOrder(user, orderId);
    }

    public List<Order> getOrdersHistory(User user) {
        return orderRepository.getAll(user);
    }

    public List<Order> getNotAcceptedOrders(User user) {
        return orderRepository.getNotAcceptedOrders(user);
    }
}
