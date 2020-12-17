package com.andersen.patterns.behavioral.mediator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SimpleUser implements User {
    private Chat chat;
    private String name;

    @Override
    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    @Override
    public void getMessage(String message) {
        log.info(this.name + " receiving message: " + message);
    }
}
