package com.andersen.multithreading.waitAndNotifyExample;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class Sender implements Runnable {
    private Data data;

    @Override
    public void run() {
        String[] packets = {"First", "Second", "Third", "Fourth", "End"};

        for (String packet : packets) {
            log.info("send: {}", packet);
            data.send(packet);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Thread interrupted");
            }
        }
    }
}
