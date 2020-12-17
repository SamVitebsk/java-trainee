package com.andersen.patterns.behavioral.chainOfResponsibility;

import lombok.Setter;

import java.util.Objects;

public abstract class Notifier {
    private int priority;
    @Setter
    private Notifier nextNotifier;

    public Notifier(int priority) {
        this.priority = priority;
    }

    public void notifyManage(String message, int level) {
        if (level >= priority) {
            write(message);
        }
        if (Objects.nonNull(nextNotifier)) {
            nextNotifier.notifyManage(message, level);
        }
    }

    public abstract void write(String message);
}
