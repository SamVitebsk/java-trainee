package com.andersen.functionalInterface;

public class Main {
    public static String convert(Integer num, Converter<String, Integer> converter) {
        return converter.convert(num);
    }
}
