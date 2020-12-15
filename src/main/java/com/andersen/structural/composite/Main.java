package com.andersen.structural.composite;

public class Main {
    public static void main(String[] args) {
        Team team = new Team();
        team.addDeveloper(new JavaDeveloper());
        team.addDeveloper(new JavaScriptDeveloper());
        team.addDeveloper(new JavaScriptDeveloper());

        team.writeCode();
    }
}
