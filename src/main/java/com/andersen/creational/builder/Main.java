package com.andersen.creational.builder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Director director = new Director();
        CarBuilder carBuilder = new CarBuilder();

        director.constructSportCar(carBuilder);
        log.info(carBuilder.getResult().toString());

        director.constructCityCar(carBuilder);
        log.info(carBuilder.getResult().toString());

        director.constructSUV(carBuilder);
        log.info(carBuilder.getResult().toString());


        ManualBuilder manualBuilder = new ManualBuilder();

        director.constructSUV(manualBuilder);
        log.info(manualBuilder.getResult().toString());

        director.constructCityCar(manualBuilder);
        log.info(manualBuilder.getResult().toString());

        director.constructSportCar(manualBuilder);
        log.info(manualBuilder.getResult().toString());
    }
}
