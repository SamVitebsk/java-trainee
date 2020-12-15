package com.andersen.behavioral.memento;

public class Main {
    public static void main(String[] args) {
        Project project = new Project();
        Repository repository = new Repository();

        project.setVersionAndDate("1.0");
        System.out.println(project);

        repository.setSave(project.save());

        project.setVersionAndDate("1.1");
        System.out.println(project);

        project.load(repository.getSave());
        System.out.println(project);
    }
}
