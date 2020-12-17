package com.andersen.patterns.behavioral.observer;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class LogOpenListener implements EventListener {
    private File logger;

    public LogOpenListener(String fileName) {
        this.logger = new File(fileName);
    }

    @Override
    public void update(String eventType, File file) {
        log.info(
                "Save to log " + logger + ": Someone has performed " + eventType + " operation with the following file: " + file.getName()
        );
    }
}
