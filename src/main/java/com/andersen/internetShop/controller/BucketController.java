package com.andersen.internetShop.controller;

import com.andersen.internetShop.service.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BucketController {
    private final BucketService bucketService;

    @GetMapping("bucket/products")
    protected String allProducts(ModelMap model) {
        model.addAttribute("products", bucketService.getProducts());

        return "bucket/products";
    }

    @GetMapping("/bucket/add-product/{productId}")
    public String addProductToBucket(@PathVariable Integer productId) {
        bucketService.addProductToBucket(productId);

        return "redirect:/warehouse/products";
    }

    @GetMapping("/bucket/delete-product/{productId}")
    protected String doGet(@PathVariable Integer productId) {
        bucketService.deleteProductFromTheBucket(productId);

        return "redirect:/bucket/products";
    }

    @GetMapping("/bucket/clear")
    protected String clearBucket() {
        bucketService.clearBucket();

        return "redirect:/main";
    }
}
