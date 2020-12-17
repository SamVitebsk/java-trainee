package com.andersen.patterns.behavioral.mediator;

public class Main {
    public static void main(String[] args) {
        TextChat chat = new TextChat();

        User admin = new Admin(chat, "Admin");
        User user1 = new SimpleUser(chat, "user 1");
        User user2 = new SimpleUser(chat, "user 2");

        chat.setAdmin(admin);
        chat.addUsersToChat(user1);
        chat.addUsersToChat(user2);

        user1.sendMessage("Hello !");
        admin.sendMessage("Hi!, I' admin");
    }
}
