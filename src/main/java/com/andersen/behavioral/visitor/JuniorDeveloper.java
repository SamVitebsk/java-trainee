package com.andersen.behavioral.visitor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JuniorDeveloper implements Developer {
    @Override
    public void create(ProjectClass projectClass) {
        log.info("Junior create class");
    }

    @Override
    public void create(Database database) {
        log.info("Junior drops database");
    }

    @Override
    public void create(Test test) {
        log.info("Junior creates test");
    }
}
