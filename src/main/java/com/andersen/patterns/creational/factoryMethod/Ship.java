package com.andersen.patterns.creational.factoryMethod;

public class Ship implements Transport {
    @Override
    public String deliver() {
        return "Deliver on the water";
    }
}
