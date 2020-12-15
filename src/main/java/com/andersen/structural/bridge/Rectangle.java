package com.andersen.structural.bridge;

public class Rectangle extends Shape {
    public Rectangle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("drawing rectangle");
        color.fillColor();
    }
}
