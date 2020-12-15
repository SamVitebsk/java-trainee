package com.andersen.creational.abstractFactory;

public class Main {
    public static void main(String[] args) {
        showInfo(new ModernFurnitureFactory());
        showInfo(new VictorianFurnitureFactory());
    }

    private static void showInfo(FurnitureFactory furnitureFactory) {
        Chair chair = furnitureFactory.createChair();
        Table table = furnitureFactory.createTable();
        Sofa sofa = furnitureFactory.createSofa();

        System.out.println(chair);
        System.out.println(table);
        System.out.println(sofa);
    }
}
