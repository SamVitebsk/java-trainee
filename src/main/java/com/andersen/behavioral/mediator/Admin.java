package com.andersen.behavioral.mediator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class Admin implements User {
    private Chat chat;
    private String name;

    @Override
    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    @Override
    public void getMessage(String message) {
        log.info("[admin] " + this.name + " receiving message: " + message);
    }
}
