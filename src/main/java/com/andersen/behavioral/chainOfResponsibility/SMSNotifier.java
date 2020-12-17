package com.andersen.behavioral.chainOfResponsibility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SMSNotifier extends Notifier {
    public SMSNotifier(int priority) {
        super(priority);
    }

    @Override
    public void write(String message) {
        log.info("Sending SMS: " + message);
    }
}
