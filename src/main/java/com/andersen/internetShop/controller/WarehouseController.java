package com.andersen.internetShop.controller;

import com.andersen.internetShop.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;

    @GetMapping("/warehouse/products")
    protected String showAllProducts(ModelMap model) {
        model.addAttribute("products",  warehouseService.getAll());

        return "warehouse/products";
    }
}
