package com.andersen.patterns.creational.factoryMethod;

public class SeaLogistics implements Logistics {
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}
