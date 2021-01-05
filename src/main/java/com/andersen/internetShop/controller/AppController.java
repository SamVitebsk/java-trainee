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
                bucketService.showProductList();
                Integer productId = getIntAnswer("Select a product ID:");
                int countProducts = getIntAnswer("Count of products:");
                boolean wasAdded = bucketService.addProductToBucket(productId, countProducts);
                if (wasAdded) {
                    log.info("*** Product was added ***");
                }
                break;
            case 3:
                boolean notEmpty = bucketService.showProductsInTheBucket();
                if (!notEmpty) {
                    log.info("*** Bucket is empty ***");
                } else {
                    productId = getIntAnswer("Select a product ID:");
                    countProducts = getIntAnswer("Count of products:");
                    boolean wasRemoved = bucketService.deleteProductFromTheBucket(productId, countProducts);
                    if (wasRemoved) {
                        log.info("*** Product was removed ***");
                    }
                }
                break;
            case 4:
                notEmpty = bucketService.showProductsInTheBucket();
                if (!notEmpty) {
                    log.info("*** Bucket is empty ***");
                }
                break;
            case 5:
                bucketService.clearBucket();
                log.info("*** Bucket cleared ***");
                break;
            case 6:
                MenuView.showCurrencyListMenu();
                Integer currencyNumber = getIntAnswer("Select a currency:");
                CurrencyCode currencyCode = CurrencyFactory.convertCurrencyIndexToCurrencyCode(currencyNumber);
                if (Objects.isNull(currencyCode)) {
                    log.info("*** Order canceled ***");
                } else {
                    Currency currency = CurrencyFactory.getCurrency(currencyCode);
                    BigDecimal total = bucketService.getTotal(currency);
                    log.info("*** Total: {} ***", total);
                    Integer confirm = getIntAnswer(" Confirm? (1 - Yes, 0 - No)");
                    if (confirm.equals(1)) {
                        orderService.makeOrder(user, total, true);
                        bucketService.clearBucket();
                        log.info("*** Order accepted, you must pay {} {}, check email ***", CurrencyCode.BYN, total);
                    } else if (confirm.equals(0)) {
                        Integer saveOrder = getIntAnswer("Save order? (1 - Yes, 0 - No)");
                        if (saveOrder.equals(1)) {
                            orderService.makeOrder(user, total, false);
                            log.info("*** Order saved ***");
                        } else {
                            bucketService.clearBucket();
                            log.info("*** Order canceled ***");
                        }
                    } else {
                        bucketService.clearBucket();
                        log.info("*** Order canceled ***");
                    }
                }
                break;
            case 7:
                List<Order> orders = orderService.getOrdersHistory(user);
                if (orders.isEmpty()) {
                    log.info("***** Orders not found *****");
                } else {
                    orders.forEach(order -> log.info("{}", order));
                }
                break;
            case 8:
                orders = orderService.getNotAcceptedOrders(user);
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
                break;
            case 0:
                authService.exit();
                break;
            default:
                bucketService.showProductList();
        }
    }

    public User authAction(Integer userInput) {
        switch (userInput) {
            case 1:
                String login = getStringAnswer("Login:");
                String password = getStringAnswer("Password:");
                User user = authService.login(login, password);
                if (Objects.nonNull(user)) {
                    log.info("*** Welcome, {}! ***", login);
                    return user;
                } else {
                    log.info("*** Invalid login or password ***");
                    return null;
                }
            case 2:
                login = getStringAnswer("Login:");
                password = getStringAnswer("Password:");
                user = authService.registration(login, password);
                if (Objects.nonNull(user)) {
                    log.info("*** Welcome, {}! ***", login);
                    return user;
                } else {
                    log.info("*** Invalid login or password ***");
                    return null;
                }
            case 0:
                authService.exit();
                System.exit(0);
            default:
                return null;
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
                new BucketRepository(user, new ProductRepository())
        );
    }
}
