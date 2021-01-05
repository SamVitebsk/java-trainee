package com.andersen.internetShop.controller;

import com.andersen.internetShop.currency.Currency;
import com.andersen.internetShop.currency.CurrencyCode;
import com.andersen.internetShop.currency.CurrencyFactory;
import com.andersen.internetShop.dao.*;
import com.andersen.internetShop.service.AuthService;
import com.andersen.internetShop.service.BucketService;
import com.andersen.internetShop.service.OrderService;
import com.andersen.internetShop.utils.MenuView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.andersen.internetShop.utils.Questioner.getIntAnswer;
import static com.andersen.internetShop.utils.Questioner.getStringAnswer;

@Slf4j
@RequiredArgsConstructor
public class AppController {
    private final AuthService authService;
    private final Warehouse warehouse;
    private final OrderService orderService;
    private BucketService bucketService;

    public void action(Integer userInput, User user) {
        if (Objects.isNull(bucketService)) {
            initBucketController(user);
        }

        switch (userInput) {
            case 2:
                addProductToBucket();
                break;
            case 3:
                deleteProductFromBucket();
                break;
            case 4:
                showProductsInBucket();
                break;
            case 5:
                bucketService.clearBucket();
                break;
            case 6:
                makeOrder(user);
                break;
            case 7:
                showOrderHistory(user);
                break;
            case 8:
                acceptSavedOrder(user);
                break;
            case 0:
                authService.exit();
                break;
            default:
                bucketService.showProductList();
                break;
        }
    }

    private void showOrderHistory(User user) {
        List<Order> orders = orderService.getOrdersHistory(user);
        if (orders.isEmpty()) {
            log.info("***** Orders not found *****");
        } else {
            orders.forEach(order -> log.info("{}", order));
        }
    }

    private void showProductsInBucket() {
        if (bucketService.bucketIsEmpty()) {
            log.info("*** Bucket is empty ***");
        } else {
            bucketService.showProductsInTheBucket();
        }
    }

    private void addProductToBucket() {
        bucketService.showProductList();
        Integer productId = getIntAnswer("Select a product ID:");
        int countProducts = getIntAnswer("Count of products:");
        boolean wasAdded = bucketService.addProductToBucket(productId, countProducts);
        if (wasAdded) {
            log.info("*** Product was added ***");
        }
    }

    private void acceptSavedOrder(User user) {
        List<Order> orders = orderService.getNotAcceptedOrders(user);
        if (orders.isEmpty()) {
            log.info("***** Orders not found *****");
        } else {
            orders.forEach(order -> log.info("{}", order));
            Integer orderId = getIntAnswer("Order id:");
            Integer accept = getIntAnswer("Accept order? (1 - Yes, 0 - No):");
            if (accept == 1) {
                orderService.acceptOrder(user, orderId);
                log.info("***** Order accepted *****");
            } else {
                log.info("***** Order canceled *****");
            }
        }
    }

    private void makeOrder(User user) {
        MenuView.showCurrencyListMenu();
        Integer currencyNumber = getIntAnswer("Select a currency:");
        CurrencyCode currencyCode = CurrencyFactory.convertCurrencyIndexToCurrencyCode(currencyNumber);
        if (Objects.isNull(currencyCode)) {
            log.info("*** Order canceled ***");
        } else {
            Currency currency = CurrencyFactory.getCurrency(currencyCode);
            BigDecimal total = bucketService.getTotal(currency);
            log.info("*** Total: {} ***", total);
            int confirm = getIntAnswer(" Confirm? (1 - Yes, 0 - No)");
            if (confirm == 1) {
                orderService.makeOrder(user, total, true);
                bucketService.clearBucket();
                log.info("*** Order accepted, you must pay {} {}, check email ***", CurrencyCode.BYN, total);
            } else if (confirm == 0) {
                Integer saveOrder = getIntAnswer("Save order? (1 - Yes, 0 - No)");
                if (saveOrder.equals(1)) {
                    orderService.makeOrder(user, total, false);
                    log.info("*** Order saved ***");
                } else {
                    log.info("*** Order canceled ***");
                }
                bucketService.clearBucket();
            } else {
                bucketService.clearBucket();
                log.info("*** Order canceled ***");
            }
        }
    }

    private void deleteProductFromBucket() {
        if (bucketService.bucketIsEmpty()) {
            log.info("*** Bucket is empty ***");
        } else {
            bucketService.showProductsInTheBucket();
            Integer productId = getIntAnswer("Select a product ID:");
            Integer countProducts = getIntAnswer("Count of products:");
            boolean wasRemoved = bucketService.deleteProductFromTheBucket(productId, countProducts);
            if (wasRemoved) {
                log.info("*** Product was removed ***");
            }
        }
    }

    public User authAction(Integer userInput) {
        User user = null;
        switch (userInput) {
            case 1:
                String login = getStringAnswer("Login:");
                String password = getStringAnswer("Password:");
                user = authService.login(login, password);
                if (Objects.nonNull(user)) {
                    log.info("*** Welcome, {}! ***", login);
                } else {
                    log.info("*** Invalid login or password ***");
                }
                return user;
            case 2:
                login = getStringAnswer("Login:");
                password = getStringAnswer("Password:");
                user = authService.registration(login, password);
                if (Objects.nonNull(user)) {
                    log.info("*** Welcome, {}! ***", login);
                } else {
                    log.info("*** Invalid login or password ***");
                }
                return user;
            case 0:
                authService.exit();
                System.exit(0);
            default:
                return user;
        }
    }


    public User authorization() {
        Integer userInput;
        User user;

        do {
            MenuView.showAuthMenu();
            userInput = getIntAnswer("Select num from 0 to 2");
            user = authAction(userInput);
        } while (Objects.isNull(user));

        return user;
    }

    private void initBucketController(User user) {
        bucketService = new BucketService(
                warehouse,
                new BucketRepository(user, new ProductRepository(), warehouse)
        );
    }
}
