package com.andersen.internetShop.controller;

import com.andersen.internetShop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/auth")
    protected String loginForm()  {
        return "auth/login-form";
    }

    @GetMapping("/registration")
    protected String registrationForm()  {
        return "/auth/registration-form";
    }

    @PostMapping("/registration")
    protected String registration(@RequestParam String login, @RequestParam String password) {
        authService.registration(login, password);

        return "redirect:/main";
    }
}
