package com.andersen.behavioral.templateMethod;

public class Main {
    public static void main(String[] args) {
        PageTemplate welcome = new WelcomePage();
        welcome.renderPage();

        System.out.println("\n==========\n");

        PageTemplate news = new NewsPage();
        news.renderPage();
    }
}
