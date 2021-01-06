package com.andersen.internetShop.servlet;

import com.andersen.internetShop.dao.Product;
import com.andersen.internetShop.dao.ProductRepository;
import com.andersen.internetShop.dao.User;
import com.andersen.internetShop.dao.Warehouse;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class WarehouseServlet extends HttpServlet implements Authenticated {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthUser(req, resp);
        log.info("{}", user);
        Warehouse warehouse = new Warehouse(new ProductRepository());
        Map<Product, Integer> products = warehouse.getAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/view/warehouse/products.jsp").forward(req, resp);
    }
}
