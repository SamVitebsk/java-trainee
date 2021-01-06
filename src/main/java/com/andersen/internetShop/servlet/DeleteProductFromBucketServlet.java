package com.andersen.internetShop.servlet;

import com.andersen.internetShop.dao.BucketRepository;
import com.andersen.internetShop.dao.ProductRepository;
import com.andersen.internetShop.dao.User;
import com.andersen.internetShop.dao.Warehouse;
import com.andersen.internetShop.service.BucketService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class DeleteProductFromBucketServlet extends HttpServlet implements Authenticated {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthUser(req, resp);

        ProductRepository productRepository = new ProductRepository();
        Warehouse warehouse = new Warehouse(productRepository);
        BucketService bucketService = new BucketService(
                warehouse,
                new BucketRepository(user, productRepository, warehouse)
        );

        Integer productId = Integer.parseInt(req.getParameter("productId"));
        bucketService.deleteProductFromTheBucket(productId);

        resp.sendRedirect("/bucket/products");
    }
}
