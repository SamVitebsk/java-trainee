package com.andersen.behavioral.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Coding implements Activity {
    @Override
    public void doIt() {
        log.info("Writing code...");
    }
}
