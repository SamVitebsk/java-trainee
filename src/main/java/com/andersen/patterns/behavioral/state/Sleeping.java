package com.andersen.patterns.behavioral.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sleeping implements Activity {
    @Override
    public void doIt() {
        log.info("Sleeping...");
    }
}
