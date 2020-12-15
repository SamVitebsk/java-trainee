package com.andersen.creational.builder;

public class Manual {
    private CarType carType;
    private Integer seats;
    private String engine;
    private Transmission transmission;
    private String gpsNavigator;

    public Manual(CarType carType, Integer seats, String engine, Transmission transmission, String gpsNavigator) {
        this.carType = carType;
        this.seats = seats;
        this.engine = engine;
        this.transmission = transmission;
        this.gpsNavigator = gpsNavigator;
    }

    public CarType getCarType() {
        return carType;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getGpsNavigator() {
        return gpsNavigator;
    }

    public void setGpsNavigator(String gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
    }

    @Override
    public String toString() {
        return "Manual{" +
                "carType=" + carType +
                ", seats=" + seats +
                ", engine='" + engine + '\'' +
                ", transmission=" + transmission +
                ", gpsNavigator='" + gpsNavigator + '\'' +
                '}';
    }
}
