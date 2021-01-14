package com.andersen.internetShop.controller;

import com.andersen.internetShop.dao.User;
import com.andersen.internetShop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainMenuController {
    private final AuthService userService;

    @GetMapping("/main")
    protected String doGet(ModelMap model) {
        User user = userService.getAuthUser();

        model.addAttribute("name", user.getLogin());
        model.addAttribute("id", user.getId().toString());

        return "main";
    }
}
