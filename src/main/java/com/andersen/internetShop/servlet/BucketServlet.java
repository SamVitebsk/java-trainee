package com.andersen.internetShop.servlet;

import com.andersen.internetShop.dao.*;
import com.andersen.internetShop.service.BucketService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class BucketServlet extends HttpServlet implements Authenticated {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthUser(req, resp);

        ProductRepository productRepository = new ProductRepository();
        Warehouse warehouse = new Warehouse(productRepository);
        BucketService bucketService = new BucketService(
                warehouse,
                new BucketRepository(user, productRepository, warehouse)
        );

        Map<Product, Integer> products = bucketService.getProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/view/bucket/products.jsp").forward(req, resp);
    }
}
