package com.andersen.creational.builder;

public class Main {
    public static void main(String[] args) {
        Director director = new Director();
        CarBuilder carBuilder = new CarBuilder();

        director.constructSportCar(carBuilder);
        System.out.println(carBuilder.getResult());

        director.constructCityCar(carBuilder);
        System.out.println(carBuilder.getResult());

        director.constructSUV(carBuilder);
        System.out.println(carBuilder.getResult());


        ManualBuilder manualBuilder = new ManualBuilder();

        director.constructSUV(manualBuilder);
        System.out.println(manualBuilder.getResult());

        director.constructCityCar(manualBuilder);
        System.out.println(manualBuilder.getResult());

        director.constructSportCar(manualBuilder);
        System.out.println(manualBuilder.getResult());
    }
}
