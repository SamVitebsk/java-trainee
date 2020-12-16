package com.andersen.behavioral.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Reading implements Activity {
    @Override
    public void doIt() {
        log.info("Reading book...");
    }
}
