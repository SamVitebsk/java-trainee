package com.andersen.patterns.behavioral.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCommand implements Command {
    private Database database;

    @Override
    public void execute() {
        database.update();
    }
}
