package com.andersen.internetShop;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MenuView {
    public static void showMainMenu() {
        log.info("************* MENU *************");
        log.info("1. Show product list");
        log.info("2. Add product to the bucket");
        log.info("3. Delete product from the bucket");
        log.info("4. Show products in the bucket");
        log.info("5. Clear bucket");
        log.info("6. Make an order");
        log.info("0. Exit");
        log.info("*********************************");
    }

    public static void showCurrencyListMenu() {
        log.info("********* CURRENCY LIST *********");
        log.info("1. Belarusian ruble");
        log.info("2. Hryvnia");
        log.info("3. US Dollar");
        log.info("0. Cancel");
        log.info("*********************************");
    }
}
