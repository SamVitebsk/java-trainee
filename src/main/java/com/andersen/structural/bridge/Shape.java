package com.andersen.structural.bridge;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Shape {
    protected Color color;

    public abstract void draw();
}
