package com.andersen.internetShop.controller;

import com.andersen.internetShop.currency.CurrencyCode;
import com.andersen.internetShop.currency.CurrencyFactory;
import com.andersen.internetShop.dao.Product;
import com.andersen.internetShop.dao.User;
import com.andersen.internetShop.service.BucketService;
import com.andersen.internetShop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController implements Authenticated {
    private final OrderService orderService;
    private final BucketService bucketService;

    @GetMapping("/order/make")
    protected String orderForm(ModelMap model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getAuthUser(req, resp);

        Map<Product, Integer> products = bucketService.getProducts();
        Map<CurrencyCode, Double> totals = new LinkedHashMap<>();
        for (CurrencyCode currencyCode : CurrencyCode.values()) {
            double totalForCurrency = bucketService.getTotal(CurrencyFactory.getCurrency(currencyCode)).doubleValue();
            totals.put(currencyCode, totalForCurrency);
        }

        model.addAttribute("products", products);
        model.addAttribute("totals", totals);

        return "/order/make";
    }

    @PostMapping("/order/make")
    protected String doPost(@RequestParam Double sum, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getAuthUser(req, resp);

        orderService.makeOrder(user, BigDecimal.valueOf(sum), false);

        return "redirect:/bucket/clear";
    }

    @GetMapping("/order/not-accepted")
    protected String notAcceptedOrders(ModelMap model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getAuthUser(req, resp);

        model.addAttribute("orders", orderService.getNotAcceptedOrders(user));

        return "order/not-accepted-orders";
    }

    @GetMapping("/order/accept/{orderId}")
    protected String acceptSavedOrder(@PathVariable Integer orderId, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getAuthUser(req, resp);

        orderService.acceptOrder(user, orderId);

        return "redirect:/order/not-accepted";
    }

    @GetMapping("/order/history")
    protected String allOrders(ModelMap model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getAuthUser(req, resp);

        model.addAttribute("orders", orderService.getOrdersHistory(user));

        return "order/orders-history";
    }
}
