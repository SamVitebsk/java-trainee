package com.andersen.patterns.structural.composite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaScriptDeveloper implements Developer {
    @Override
    public void writeCode() {
        log.info("JavaScript developer writes code");
    }
}
