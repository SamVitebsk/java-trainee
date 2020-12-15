package com.andersen.creational.prototype;

import java.util.Objects;

public abstract class Shape {
    protected Integer x;
    protected Integer y;
    protected String color;

    public Shape() {
    }

    public Shape(Shape target) {
        if (target != null) {
            this.x = target.x;
            this.y = target.y;
            this.color = target.color;
        }
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public abstract Shape clone();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Objects.equals(x, shape.x) &&
                Objects.equals(y, shape.y) &&
                Objects.equals(color, shape.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, color);
    }
}
