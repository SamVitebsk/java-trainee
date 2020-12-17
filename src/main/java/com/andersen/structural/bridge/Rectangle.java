package com.andersen.structural.bridge;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Rectangle extends Shape {
    public Rectangle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        log.info("drawing rectangle");
        color.fillColor();
    }
}
