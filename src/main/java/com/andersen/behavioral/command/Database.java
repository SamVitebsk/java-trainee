package com.andersen.behavioral.command;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Database {
    public void insert() {
        log.info("Inserting record...");
    }

    public void update() {
        log.info("Updating record...");
    }

    public void select() {
        log.info("Reading record...");
    }

    public void delete() {
        log.info("Deleting record...");
    }
}
