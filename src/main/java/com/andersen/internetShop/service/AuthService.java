package com.andersen.internetShop.service;

import com.andersen.internetShop.dao.User;
import com.andersen.internetShop.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private User user;

    public User login(String login, String password) {
        user = userRepository.getByLoginAndPassword(login, password);
        return user;
    }

    public User registration(String login, String password) {
        user = userRepository.create(login, password);
        return user;
    }

    public User getAuthUser() {
        return user;
    }

    public void exit() {
        log.info("Good bye");
    }
}
