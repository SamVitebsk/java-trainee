package com.andersen.patterns.structural.flyweight;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class VehicleFactory {
    private static Map<Color, Vehicle> vehiclesCache = new HashMap<>();

    private VehicleFactory() {}

    public static Vehicle createVehicle(Color color) {
        return vehiclesCache.computeIfAbsent(color, newColor -> new Car(new Engine(), newColor));
    }
}
