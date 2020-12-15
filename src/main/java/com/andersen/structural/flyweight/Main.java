package com.andersen.structural.flyweight;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle1 = VehicleFactory.createVehicle(Color.BLACK);
        Vehicle vehicle2 = VehicleFactory.createVehicle(Color.BLACK);

        System.out.println(vehicle1 == vehicle2);
        System.out.println(vehicle1.equals(vehicle2));
    }
}
