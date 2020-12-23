package com.andersen.multithreading.joinExample;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            log.info(Thread.currentThread().getName() + ": start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("Error: " + Thread.currentThread().getName());
            }
            log.info(Thread.currentThread().getName() + ": end" );
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread1.join();

        thread2.start();
        thread3.start();

        thread2.join();
        thread3.join();

        log.info("End.");
    }
}
