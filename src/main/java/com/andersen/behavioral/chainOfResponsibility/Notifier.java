package com.andersen.behavioral.chainOfResponsibility;

public abstract class Notifier {
    private int priority;
    private Notifier nextNotifier;

    public Notifier(int priority) {
        this.priority = priority;
    }

    public void setNextNotifier(Notifier nextNotifier) {
        this.nextNotifier = nextNotifier;
    }

    public void notifyManage(String message, int level) {
        if (level >= priority) {
            write(message);
        }
        if (nextNotifier != null) {
            nextNotifier.notifyManage(message, level);
        }
    }

    public abstract void write(String message);
}
