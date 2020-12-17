package com.andersen.patterns.structural.bridge;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Shape {
    protected Color color;

    public abstract void draw();
}
