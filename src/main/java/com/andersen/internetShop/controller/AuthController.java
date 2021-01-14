package com.andersen.internetShop.controller;

import com.andersen.internetShop.dao.User;
import com.andersen.internetShop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/auth")
    protected String loginForm()  {
        return "auth/login-form";
    }

    @PostMapping("/auth")
    protected String login(@RequestParam String login, @RequestParam String password) {
        User user = authService.login(login, password);

        if (Objects.isNull(user)) {
            return "redirect:/auth";
        }

        return "redirect:/main";
    }

    @GetMapping("/registration")
    protected String doGet()  {
        return "/auth/registration-form";
    }

    @PostMapping("/registration")
    protected String doPost(@RequestParam String login, @RequestParam String password) {
        User user = authService.registration(login, password);

        if (Objects.isNull(user)) {
            return "redirect:/auth";
        }

        return "redirect:/main";
    }
}
