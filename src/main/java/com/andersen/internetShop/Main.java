package com.andersen.internetShop;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static MainController controller = new MainController(
            new ProductRepository(),
            new Bucket()
    );

    public static void main(String[] args) {
        Long userInput;

        do {
            showMainMenu();
            userInput = askQuestion("Select num from 0 to 5");
            action(userInput);
        } while (userInput != 0);
    }

    private static void action(Long userInput) {
        switch (userInput.intValue()) {
            case 2:
                controller.showProductList();
                controller.addProductToBucket(
                        askQuestion("Select a product:"));
                break;
            case 3:
                boolean notEmpty = controller.showProductsInTheBucket();
                if (notEmpty) {
                    controller.deleteProductFromTheBucket(
                            askQuestion("Select a product:")
                    );
                    System.out.println("*** Product was removed ***");
                } else {
                    System.out.println("*** Bucket is empty ***");
                }
                break;
            case 4:
                notEmpty = controller.showProductsInTheBucket();
                if (!notEmpty) {
                    System.out.println("*** Bucket is empty ***");
                }
                break;
            case 5:
                controller.clearBucket();
                System.out.println("*** Bucket cleared ***");
                break;
            case 0:
                controller.exit();
                break;
            default:
                controller.showProductList();
        }

    }

    private static Long askQuestion(String question) {
        System.out.println(question);
        try {
            return Long.parseLong(reader.readLine());
        } catch (Exception e) {
            log.error(e.getMessage());
            return 1L;
        }
    }

    private static void showMainMenu() {
        System.out.println("************* MENU *************");
        System.out.println("1. Show product list");
        System.out.println("2. Add product to the bucket");
        System.out.println("3. Delete product from the bucket");
        System.out.println("4. Show products in the bucket");
        System.out.println("5. Clear bucket");
        System.out.println("0. Exit");
        System.out.println("*********************************");
    }
}
