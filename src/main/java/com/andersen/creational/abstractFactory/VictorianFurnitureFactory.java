package com.andersen.creational.abstractFactory;

public class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair("victorian material");
    }

    @Override
    public Table createTable() {
        return new VictorianTable(321f);
    }

    @Override
    public Sofa createSofa() {
        return new VictorianSofa(123f);
    }
}
