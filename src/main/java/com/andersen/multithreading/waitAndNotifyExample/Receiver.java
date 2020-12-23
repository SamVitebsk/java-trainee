package com.andersen.multithreading.waitAndNotifyExample;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class Receiver implements Runnable {
    private Data data;

    @Override
    public void run() {
        for (String message = data.receive(); !"End".equals(message); message = data.receive()) {
            log.info("receive: " + message);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Thread interrupted");
            }
        }
    }
}
