package com.andersen.behavioral.visitor;

public class JuniorDeveloper implements Developer {
    @Override
    public void create(ProjectClass projectClass) {
        System.out.println("Junior create class");
    }

    @Override
    public void create(Database database) {
        System.out.println("Junior drops database");
    }

    @Override
    public void create(Test test) {
        System.out.println("Junior creates test");
    }
}
