package com.andersen.patterns.creational.abstractFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        showInfo(new ModernFurnitureFactory());
        showInfo(new VictorianFurnitureFactory());
    }

    private static void showInfo(FurnitureFactory furnitureFactory) {
        Chair chair = furnitureFactory.createChair();
        Table table = furnitureFactory.createTable();
        Sofa sofa = furnitureFactory.createSofa();

        log.info(chair.toString());
        log.info(table.toString());
        log.info(sofa.toString());
    }
}
