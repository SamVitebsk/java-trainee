package com.andersen.internetShop.controller;

import com.andersen.internetShop.dao.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainMenuController implements Authenticated {
    @GetMapping("/main")
    protected String doGet(ModelMap model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getAuthUser(req, resp);

        model.addAttribute("name", user.getLogin());
        model.addAttribute("id", user.getId().toString());

        return "main";
    }
}
