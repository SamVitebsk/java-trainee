package com.andersen.internetShop;

import com.andersen.internetShop.controller.ConsoleAppController;
import com.andersen.internetShop.dao.*;
import com.andersen.internetShop.service.AuthService;
import com.andersen.internetShop.service.OrderService;
import com.andersen.internetShop.utils.MenuView;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.andersen.internetShop.utils.Questioner.getIntAnswer;

public class App {
    private final ConsoleAppController consoleAppController;

    public App() {
        consoleAppController = new ConsoleAppController(
                new AuthService(new UserRepository(new JdbcTemplate())),
                new WarehouseRepository(new ProductRepository(new JdbcTemplate()), new JdbcTemplate()),
                new OrderService(new OrderRepository(new JdbcTemplate()))
        );
    }

    public static void main(String[] args) {
        new App().start();
    }

    public void start() {
        User user = consoleAppController.authorization();

        int userInput;
        do {
            MenuView.showMainMenu();
            userInput = getIntAnswer("Select number from 0 to 8");
            consoleAppController.action(userInput, user);
        } while (userInput != 0);
    }
}
