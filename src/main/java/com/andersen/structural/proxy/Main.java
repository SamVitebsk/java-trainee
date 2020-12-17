package com.andersen.structural.proxy;

public class Main {
    public static void main(String[] args) {
        Project project = new ProxyProject("github.com/123");
        project.run();
    }
}
