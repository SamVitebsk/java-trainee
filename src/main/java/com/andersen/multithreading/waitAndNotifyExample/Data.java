package com.andersen.multithreading.waitAndNotifyExample;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Data {
    private String packet;
    private boolean transfer = true;

    public synchronized void send(String paket) {
        while (!transfer) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Thread interrupted");
            }
        }

        transfer = false;

        this.packet = paket;
        notifyAll();
    }

    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Thread interrupted");
            }
        }

        transfer = true;
        notifyAll();

        return packet;
    }
}
