package com.andersen.patterns.creational.builder;

public class Director {
    public void constructSportCar(Builder builder) {
        builder.setCarType(CarType.SPORTS_CAR);
        builder.setEngine("5.0");
        builder.setSeats(2);
        builder.setTransmission(Transmission.AUTOMATIC);
        builder.setGpsNavigator("gps navigator");
    }
    public void constructCityCar(Builder builder) {
        builder.setCarType(CarType.CITY_CAR);
        builder.setEngine("2.0");
        builder.setSeats(4);
        builder.setTransmission(Transmission.SEMI_AUTOMATIC);
        builder.setGpsNavigator("gps navigator");
    }
    public void constructSUV(Builder builder) {
        builder.setCarType(CarType.SUV);
        builder.setEngine("8.0");
        builder.setSeats(3);
        builder.setTransmission(Transmission.MANUAL);
        builder.setGpsNavigator("gps navigator");
    }
}
