package com.andersen.behavioral.visitor;

public class Project implements ProjectElement {
    ProjectElement[] projectElements;

    public Project() {
        this.projectElements = new ProjectElement[]{
                new Test(),
                new Database(),
                new ProjectClass()
        };
    }

    @Override
    public void beWritten(Developer developer) {
        for (ProjectElement projectElement : projectElements) {
            projectElement.beWritten(developer);
        }
    }
}
