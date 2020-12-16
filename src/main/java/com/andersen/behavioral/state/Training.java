package com.andersen.behavioral.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Training implements Activity {
    @Override
    public void doIt() {
        log.info("Training...");
    }
}
