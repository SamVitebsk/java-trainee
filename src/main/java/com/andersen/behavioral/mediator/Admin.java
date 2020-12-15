package com.andersen.behavioral.mediator;

public class Admin implements User {
    private Chat chat;
    private String name;

    public Admin(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    @Override
    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("[admin] " + this.name + " receiving message: " + message);
    }
}
