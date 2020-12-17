package com.andersen.patterns.structural.adapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemoryCard {
    public void insert() {
        log.info("insert!");
    }

    public void copyData() {
        log.info("copy data!");
    }
}
