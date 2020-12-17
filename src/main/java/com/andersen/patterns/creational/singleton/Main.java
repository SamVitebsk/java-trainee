package com.andersen.patterns.creational.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        log.info(String.valueOf(s1 == s2));
        log.info(String.valueOf(s1.equals(s2)));
    }
}
