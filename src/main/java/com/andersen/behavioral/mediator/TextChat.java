package com.andersen.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

public class TextChat implements Chat {
    private User admin;
    private List<User> users = new ArrayList<>();

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public void addUsersToChat(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String message, User user) {
        for (User person : users) {
            if (person != user) {
                person.getMessage(message);
            }
        }
        admin.getMessage(message);
    }
}
