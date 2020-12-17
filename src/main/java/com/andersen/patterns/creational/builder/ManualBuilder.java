package com.andersen.patterns.creational.builder;

public class ManualBuilder implements Builder {
    private CarType carType;
    private Integer seats;
    private String engine;
    private Transmission transmission;
    private String gpsNavigator;

    @Override
    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    @Override
    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Override
    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public void setGpsNavigator(String navigator) {
        this.gpsNavigator = navigator;
    }

    public Manual getResult() {
        return new Manual(this.carType, this.seats, this.engine, this.transmission, this.gpsNavigator);
    }
}
