package com.andersen.behavioral.visitor;

public class SeniorDeveloper implements Developer {
    @Override
    public void create(ProjectClass projectClass) {
        System.out.println("Senior create class");
    }

    @Override
    public void create(Database database) {
        System.out.println("Senior create database");
    }

    @Override
    public void create(Test test) {
        System.out.println("Senior create test");
    }
}
