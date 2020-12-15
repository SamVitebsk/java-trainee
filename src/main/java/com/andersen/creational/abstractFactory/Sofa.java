package com.andersen.creational.abstractFactory;

public abstract class Sofa {
    protected Float cost;

    public Sofa(Float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Sofa{" +
                "cost=" + cost +
                '}';
    }
}
