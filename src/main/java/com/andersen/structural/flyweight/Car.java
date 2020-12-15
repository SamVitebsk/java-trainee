package com.andersen.structural.flyweight;

import java.awt.*;
import java.util.Objects;

public class Car implements Vehicle {
    private Engine engine;
    private Color color;

    public Car(Engine engine, Color color) {
        this.engine = engine;
        this.color = color;
    }


    @Override
    public void start() {
        System.out.println("Car is starting");
        engine.start();
    }

    @Override
    public void stop() {
        System.out.println("Car is stopping");
        engine.stop();
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(engine, car.engine) &&
                Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engine, color);
    }
}
