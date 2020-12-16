package com.andersen.behavioral.memento;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Save {
    private String version;
    private LocalDate date;

    public Save(String version) {
        this.version = version;
        this.date = LocalDate.now();
    }
}
