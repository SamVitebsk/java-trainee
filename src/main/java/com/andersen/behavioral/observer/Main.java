package com.andersen.behavioral.observer;

public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.events.subscribe("open", new LogOpenListener("/home/sam/projects/java-trainee/src/main/java/com/andersen/behavioral/observer/log.txt"));
        editor.events.subscribe("save", new EmailNotificationListener("admin@.mail.com"));

        try {
            editor.openFile("test.txt");
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
