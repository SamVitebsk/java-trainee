package com.andersen.behavioral.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCommand implements Command {
    private Database database;

    @Override
    public void execute() {
        database.delete();
    }
}
