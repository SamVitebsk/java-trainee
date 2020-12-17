package com.andersen.patterns.behavioral.visitor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SeniorDeveloper implements Developer {
    @Override
    public void create(ProjectClass projectClass) {
        log.info("Senior create class");
    }

    @Override
    public void create(Database database) {
        log.info("Senior create database");
    }

    @Override
    public void create(Test test) {
        log.info("Senior create test");
    }
}
