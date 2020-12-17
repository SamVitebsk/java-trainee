package com.andersen.patterns.behavioral.templateMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        PageTemplate welcome = new WelcomePage();
        welcome.renderPage();

        log.info("\n==========\n");

        PageTemplate news = new NewsPage();
        news.renderPage();
    }
}
