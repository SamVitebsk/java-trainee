package com.andersen.patterns.structural.bridge;

public class Main {
    public static void main(String[] args) {
        Shape shape = new Rectangle(new BlackColor());
        shape.draw();

        shape = new Square(new RedColor());
        shape.draw();
    }
}
