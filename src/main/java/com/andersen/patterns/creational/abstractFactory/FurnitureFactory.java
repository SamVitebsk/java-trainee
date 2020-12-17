package com.andersen.patterns.creational.abstractFactory;

public interface FurnitureFactory {
    Chair createChair();
    Table createTable();
    Sofa createSofa();
}
