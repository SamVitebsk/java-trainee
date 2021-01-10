package com.andersen.internetShop.servlet;

import com.andersen.internetShop.currency.CurrencyCode;
import com.andersen.internetShop.currency.CurrencyFactory;
import com.andersen.internetShop.dao.*;
import com.andersen.internetShop.service.BucketService;
import com.andersen.internetShop.service.OrderService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class OrderServlet extends HttpServlet implements Authenticated {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthUser(req, resp);

        ProductRepository productRepository = new ProductRepository();
        Warehouse warehouse = new Warehouse(productRepository);
        BucketService bucketService = new BucketService(
                warehouse,
                new BucketRepository(user, productRepository, warehouse)
        );

        Map<Product, Integer> products = bucketService.getProducts();
        Map<CurrencyCode, Double> totals = new LinkedHashMap<>();
        for (CurrencyCode currencyCode : CurrencyCode.values()) {
            double totalForCurrency = bucketService.getTotal(CurrencyFactory.getCurrency(currencyCode)).doubleValue();
            totals.put(currencyCode, totalForCurrency);
        }

        req.setAttribute("products", products);
        req.setAttribute("totals", totals);
        req.getRequestDispatcher("/WEB-INF/view/order/make.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthUser(req, resp);

        double total = Double.parseDouble(req.getParameter("sum"));
        OrderService orderService = new OrderService(new OrderRepository());
        orderService.makeOrder(user, BigDecimal.valueOf(total), false);
        resp.sendRedirect("/bucket/clear");
    }
}
