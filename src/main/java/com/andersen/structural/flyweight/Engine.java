package com.andersen.structural.flyweight;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Engine {
    public void start() {
        log.info("Engine start");
    }

    public void stop() {
        log.info("Engine stop");
    }
}
