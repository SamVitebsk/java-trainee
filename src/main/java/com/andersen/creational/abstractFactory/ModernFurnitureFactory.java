package com.andersen.creational.abstractFactory;

public class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair("modern material");
    }

    @Override
    public Table createTable() {
        return new ModernTable(123f);
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa(1000f);
    }
}
