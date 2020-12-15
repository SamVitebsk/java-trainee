package com.andersen.behavioral.memento;

import java.time.LocalDate;

public class Save {
    private String version;
    private LocalDate date;

    public Save(String version) {
        this.version = version;
        this.date = LocalDate.now();
    }

    public String getVersion() {
        return version;
    }

    public LocalDate getDate() {
        return date;
    }
}
