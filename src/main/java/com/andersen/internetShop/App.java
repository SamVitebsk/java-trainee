package com.andersen.internetShop;

import com.andersen.internetShop.controller.AppController;
import com.andersen.internetShop.dao.OrderRepository;
import com.andersen.internetShop.dao.User;
import com.andersen.internetShop.dao.UserRepository;
import com.andersen.internetShop.dao.Warehouse;
import com.andersen.internetShop.service.AuthService;
import com.andersen.internetShop.service.OrderService;
import com.andersen.internetShop.utils.MenuView;

import static com.andersen.internetShop.utils.Questioner.getIntAnswer;

public class App {
    private final AppController appController;

    public App() {
        appController = new AppController(
                new AuthService(new UserRepository()),
                new Warehouse(),
                new OrderService(new OrderRepository())
        );
    }

    public static void main(String[] args) {
        new App().start();
    }

    public void start() {
        User user = appController.authorization();

        int userInput;
        do {
            MenuView.showMainMenu();
            userInput = getIntAnswer("Select number from 0 to 5");
            appController.action(userInput, user);
        } while (userInput != 0);
    }
}
