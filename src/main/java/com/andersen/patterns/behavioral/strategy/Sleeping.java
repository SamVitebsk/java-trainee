package com.andersen.patterns.behavioral.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sleeping implements Activity {
    @Override
    public void doIt() {
        log.info("Sleeping...");
    }
}
