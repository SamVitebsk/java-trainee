package com.andersen.patterns.structural.flyweight;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Vehicle vehicle1 = VehicleFactory.createVehicle(Color.BLACK);
        Vehicle vehicle2 = VehicleFactory.createVehicle(Color.BLACK);

        log.info(String.valueOf(vehicle1 == vehicle2));
        log.info(String.valueOf(vehicle1.equals(vehicle2)));
    }
}
