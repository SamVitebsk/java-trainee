package com.andersen.patterns.behavioral.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Developer {
    private Command insert;
    private Command update;
    private Command select;
    private Command delete;

    public void insertRecord() {
        insert.execute();
    }

    public void updateRecord() {
        update.execute();
    }

    public void selectRecord() {
        select.execute();
    }

    public void deleteRecord() {
        delete.execute();
    }
}
