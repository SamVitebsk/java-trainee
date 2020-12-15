package com.andersen.behavioral.memento;

import java.time.LocalDate;

public class Project {
    private String version;
    private LocalDate date;

    public void setVersionAndDate(String version) {
        this.version = version;
        this.date = LocalDate.now();
    }

    public Save save() {
        return new Save(version);
    }

    public void load(Save save) {
        this.version = save.getVersion();
        this.date = save.getDate();
    }

    @Override
    public String toString() {
        return "Project{" +
                "version='" + version + '\'' +
                ", date=" + date +
                '}';
    }
}
