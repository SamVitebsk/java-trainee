package com.andersen.internetShop;

public class MenuView {
    public static void showMainMenu() {
        System.out.println("************* MENU *************");
        System.out.println("1. Show product list");
        System.out.println("2. Add product to the bucket");
        System.out.println("3. Delete product from the bucket");
        System.out.println("4. Show products in the bucket");
        System.out.println("5. Clear bucket");
        System.out.println("6. Make an order");
        System.out.println("0. Exit");
        System.out.println("*********************************");
    }

    public static void showCurrencyListMenu() {
        System.out.println("********* CURRENCY LIST *********");
        System.out.println("1. Belarusian ruble");
        System.out.println("2. Hryvnia");
        System.out.println("3. US Dollar");
        System.out.println("0. Cancel");
        System.out.println("*********************************");
    }
}
