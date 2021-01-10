package com.andersen.internetShop.servlet;

import com.andersen.internetShop.dao.Order;
import com.andersen.internetShop.dao.OrderRepository;
import com.andersen.internetShop.dao.User;
import com.andersen.internetShop.service.OrderService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
public class NotAcceptedOrdersServlet extends HttpServlet implements Authenticated {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthUser(req, resp);

        OrderService orderService = new OrderService(new OrderRepository());
        List<Order> orders = orderService.getNotAcceptedOrders(user);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/view/order/not-accepted-orders.jsp").forward(req, resp);
    }
}
