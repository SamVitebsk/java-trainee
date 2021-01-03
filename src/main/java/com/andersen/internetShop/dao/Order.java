package com.andersen.internetShop.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Order {
    private LocalDate createdAt;
//    private Bucket bucket;
    private UUID userId;
    private BigDecimal total;
    private boolean accepted;
}
