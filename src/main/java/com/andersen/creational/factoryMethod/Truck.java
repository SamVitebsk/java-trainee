package com.andersen.creational.factoryMethod;

public class Truck implements Transport {
    @Override
    public String deliver() {
        return "Drive on the road";
    }
}
