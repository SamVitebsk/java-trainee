package com.andersen.creational.builder;

public interface Builder {
    void setCarType(CarType carType);
    void setSeats(Integer seats);
    void setEngine(String engine);
    void setTransmission(Transmission transmission);
    void setGpsNavigator(String navigator);
}
