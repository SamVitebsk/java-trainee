package com.andersen.internetShop.servlet;

import com.andersen.internetShop.dao.User;
import com.andersen.internetShop.dao.UserRepository;
import com.andersen.internetShop.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/auth/login-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AuthService authService = new AuthService(new UserRepository());
        User user = authService.login(
                req.getParameter("login"),
                req.getParameter("password")
        );

        if (Objects.isNull(user)) {
            resp.sendRedirect("/auth");
        }

        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/main");
    }
}
