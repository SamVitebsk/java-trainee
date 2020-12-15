package com.andersen.creational.abstractFactory;

public abstract class Chair {
    protected String material;

    public Chair(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Chair{" +
                "material='" + material + '\'' +
                '}';
    }
}
