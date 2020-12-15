package com.andersen.structural.bridge;

public class Square extends Shape {
    public Square(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("drawing square");
        color.fillColor();
    }
}
