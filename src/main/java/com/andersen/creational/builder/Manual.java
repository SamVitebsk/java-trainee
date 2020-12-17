package com.andersen.creational.builder;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class Manual {
    private CarType carType;
    private Integer seats;
    private String engine;
    private Transmission transmission;
    private String gpsNavigator;
}
