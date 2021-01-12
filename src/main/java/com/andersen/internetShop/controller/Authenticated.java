package com.andersen.internetShop.controller;

import com.andersen.internetShop.dao.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public interface Authenticated {
    default User getAuthUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (Objects.isNull(user)) {
            resp.getWriter().write("access denied");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return user;
    }
}
