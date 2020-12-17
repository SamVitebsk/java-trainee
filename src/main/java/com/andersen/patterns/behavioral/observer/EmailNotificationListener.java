package com.andersen.patterns.behavioral.observer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
@AllArgsConstructor
public class EmailNotificationListener implements EventListener {
    private String email;

    @Override
    public void update(String eventType, File file) {
        log.info(
                "Email to " + email + ": Someone has performed " + eventType + " operation with the following file: " + file.getName()
        );
    }
}
