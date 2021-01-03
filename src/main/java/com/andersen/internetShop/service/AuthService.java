package com.andersen.internetShop.service;

import com.andersen.internetShop.dao.User;
import com.andersen.internetShop.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public User login(String login, String password) {
        return userRepository.getByLoginAndPassword(login, password);
    }

    public User registration(String login, String password) {
        return userRepository.create(login, password);
    }

    public void exit() {
        log.info("Good bye");
    }
}
