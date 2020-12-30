package com.andersen.internetShop;

import com.andersen.internetShop.currency.Currency;
import com.andersen.internetShop.currency.CurrencyCode;
import com.andersen.internetShop.currency.CurrencyFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
public class Main {
    private static final String BUCKET_FILE_NAME = "bucket.txt";
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final MainController controller;
    private static final Bucket bucket;
    private static final Warehouse warehouse;

    static {
        bucket = BucketSaver.load(BUCKET_FILE_NAME);
        warehouse = new Warehouse();
        controller = new MainController(warehouse, bucket);
    }

    public static void main(String[] args) {
        warehouse.boot();
        Integer userInput;

        do {
            MenuView.showMainMenu();
            userInput = askQuestion("Select num from 0 to 5");
            action(userInput);
        } while (userInput != 0);
    }

    private static void action(Integer userInput) {
        switch (userInput) {
            case 2:
                controller.showProductList();
                Integer productId = askQuestion("Select a product:");
                int countProducts = askQuestion("Count of products:");
                boolean wasAdded = controller.addProductToBucket(productId, countProducts);
                if (wasAdded) {
                    System.out.println("*** Product was added ***");
                }
                break;
            case 3:
                controller.showProductsInTheBucket();
                productId = askQuestion("Select a product:");
                countProducts = askQuestion("Count of products:");
                boolean wasRemoved = controller.deleteProductFromTheBucket(productId, countProducts);
                if (wasRemoved) {
                    System.out.println("*** Product was removed ***");
                }
                break;
            case 4:
                boolean notEmpty = controller.showProductsInTheBucket();
                if (!notEmpty) {
                    System.out.println("*** Bucket is empty ***");
                }
                break;
            case 5:
                controller.clearBucket();
                System.out.println("*** Bucket cleared ***");
                break;
            case 6:
                MenuView.showCurrencyListMenu();
                Integer currencyNumber = askQuestion("Select a currency:");
                CurrencyCode currencyCode = CurrencyFactory.convertCurrencyIndexToCurrencyCode(currencyNumber);
                if (Objects.isNull(currencyCode)) {
                    System.out.println("*** Order canceled ***");
                } else {
                    Currency currency = CurrencyFactory.getCurrency(currencyCode);
                    BigDecimal total = controller.makeOrder(currency);
                    System.out.printf("*** Order accepted, you must pay %s %s, check email ***\n", CurrencyCode.BYN, total);
                }
                break;
            case 0:
                BucketSaver.saveBucket(bucket, BUCKET_FILE_NAME);
                controller.exit();
                break;
            default:
                controller.showProductList();
        }

    }

    private static Integer askQuestion(String question) {
        System.out.println(question);
        try {
            return Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            log.error(e.getMessage());
            return -1;
        }
    }
}
