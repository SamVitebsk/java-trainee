package com.andersen.creational.factoryMethod;

public class Ship implements Transport {
    @Override
    public String deliver() {
        return "Deliver on the water";
    }
}
