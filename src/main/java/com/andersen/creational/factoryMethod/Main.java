package com.andersen.creational.factoryMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Logistics logistics = getLogistic("sea");
        Transport transport = logistics.createTransport();

        log.info(transport.deliver());
    }

    private static Logistics getLogistic(String type) {
        if ("sea".equalsIgnoreCase(type)) {
            return new SeaLogistics();
        } else {
            return new RoadLogistics();
        }
    }
}
