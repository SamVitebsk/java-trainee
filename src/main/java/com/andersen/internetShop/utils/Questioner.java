package com.andersen.internetShop.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class Questioner {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String getStringAnswer(String question) {
        log.info(question);
        try {
            return reader.readLine().trim();
        } catch (Exception e) {
            log.error("Read line exception: {}", e.getMessage());
            return "-1";
        }
    }

    public static Integer getIntAnswer(String question) {
        log.info(question);
        try {
            return Integer.parseInt(reader.readLine().trim());
        } catch (Exception e) {
            log.error("Parse int exception: {}", e.getMessage());
            return -1;
        }
    }
}
