package com.andersen.behavioral.visitor;

public class Main {
    public static void main(String[] args) {
        Developer juniorDeveloper = new JuniorDeveloper();
        Developer seniorDeveloper = new SeniorDeveloper();
        Project project = new Project();

        project.beWritten(juniorDeveloper);
        project.beWritten(seniorDeveloper);
    }
}
