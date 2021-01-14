package com.andersen.internetShop.controller;

import com.andersen.internetShop.currency.CurrencyCode;
import com.andersen.internetShop.currency.CurrencyFactory;
import com.andersen.internetShop.dao.Product;
import com.andersen.internetShop.service.AuthService;
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

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {
    private final AuthService userService;
    private final OrderService orderService;
    private final BucketService bucketService;

    @GetMapping("/order/make")
    protected String orderForm(ModelMap model) {
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
    protected String doPost(@RequestParam Double sum) {
        orderService.makeOrder(userService.getAuthUser(), BigDecimal.valueOf(sum), false);

        return "redirect:/bucket/clear";
    }

    @GetMapping("/order/not-accepted")
    protected String notAcceptedOrders(ModelMap model) {
        model.addAttribute("orders", orderService.getNotAcceptedOrders(userService.getAuthUser()));

        return "order/not-accepted-orders";
    }

    @GetMapping("/order/accept/{orderId}")
    protected String acceptSavedOrder(@PathVariable Integer orderId) {
        orderService.acceptOrder(userService.getAuthUser(), orderId);

        return "redirect:/order/not-accepted";
    }

    @GetMapping("/order/history")
    protected String allOrders(ModelMap model) {
        model.addAttribute("orders", orderService.getOrdersHistory(userService.getAuthUser()));

        return "order/orders-history";
    }
}
