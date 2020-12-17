package com.andersen.patterns.creational.prototype;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@NoArgsConstructor
public abstract class Shape {
    protected Integer x;
    protected Integer y;
    protected String color;

    public Shape(Shape target) {
        if (Objects.nonNull(target)) {
            this.x = target.x;
            this.y = target.y;
            this.color = target.color;
        }
    }

    public abstract Shape clone();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (Objects.isNull(o) || getClass() != o.getClass()) return false;
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
