package com.andersen.structural.bridge;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Square extends Shape {
    public Square(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        log.info("drawing square");
        color.fillColor();
    }
}
