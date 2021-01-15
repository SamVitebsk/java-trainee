package com.andersen.internetShop.service;

import com.andersen.internetShop.dao.User;
import com.andersen.internetShop.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public void registration(String login, String password) {
        userRepository.create(login, password);
        User user = getByLogin(login);
        log.info("registration {}", user);
    }

    public User getAuthUser() {
        return getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public User getByLogin(String login) {
        return (User) userRepository.getByLogin(login);
    }
}
