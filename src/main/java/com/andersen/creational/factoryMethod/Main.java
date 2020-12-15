package com.andersen.creational.factoryMethod;

public class Main {
    public static void main(String[] args) {
//        Logistics logistics = new RoadLogistics();
        Logistics logistics = new SeaLogistics();
        Transport transport = logistics.createTransport();

        System.out.println(transport.deliver());
    }
}
