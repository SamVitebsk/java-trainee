package com.andersen.structural.flyweight;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class Car implements Vehicle {
    private Engine engine;
    private Color color;

    @Override
    public void start() {
        log.info("Car is starting");
        engine.start();
    }

    @Override
    public void stop() {
        log.info("Car is stopping");
        engine.stop();
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (Objects.isNull(o) || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(engine, car.engine) &&
                Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engine, color);
    }
}
