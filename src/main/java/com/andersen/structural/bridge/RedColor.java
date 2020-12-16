package com.andersen.structural.bridge;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedColor implements Color {
    @Override
    public void fillColor() {
        log.info("Filling in red color");
    }
}
