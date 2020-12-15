package com.andersen.behavioral.strategy;

public class Reading implements Activity {
    @Override
    public void doIt() {
        System.out.println("Reading book...");
    }
}
