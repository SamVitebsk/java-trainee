package com.andersen.internetShop.dao;

public class BaseRepository {
    protected static final String DB_USER = "admin";
    protected static final String DB_PASSWORD = "admin";
    protected static final String DB_URL = "jdbc:mysql://localhost:3306/shop?serverTimezone=Europe/Moscow";

    public BaseRepository() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Mysql Driver Exception: " + e.getMessage());
        }
    }
}
