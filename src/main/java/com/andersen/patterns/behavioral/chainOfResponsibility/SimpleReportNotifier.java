package com.andersen.patterns.behavioral.chainOfResponsibility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleReportNotifier extends Notifier {
    public SimpleReportNotifier(int priority) {
        super(priority);
    }

    @Override
    public void write(String message) {
        log.info("Simple report: " + message);
    }
}
