package com.andersen.behavioral.memento;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Project project = new Project();
        Repository repository = new Repository();

        project.setVersionAndDate("1.0");
        log.info(project.toString());

        repository.setSave(project.save());

        project.setVersionAndDate("1.1");
        log.info(project.toString());

        project.load(repository.getSave());
        log.info(project.toString());
    }
}
