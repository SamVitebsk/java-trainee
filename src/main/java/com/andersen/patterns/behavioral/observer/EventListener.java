package com.andersen.patterns.behavioral.observer;

import java.io.File;

public interface EventListener {
    void update(String eventType, File file);
}
