package com.andersen.patterns.creational.factoryMethod;

public class Truck implements Transport {
    @Override
    public String deliver() {
        return "Drive on the road";
    }
}
