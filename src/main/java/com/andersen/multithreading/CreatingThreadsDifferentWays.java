package com.andersen.multithreading;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CreatingThreadsDifferentWays {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread simpleThread = new SimpleThread();
        simpleThread.start();

        new Thread(() -> log.info("{}: runnable", Thread.currentThread().getName()))
                .start();

        Callable<String> callable = () -> {
            log.info("{}: callable", Thread.currentThread().getName());
            return "callable";
        };
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            Future<String> future = singleThreadExecutor.submit(
                    () -> Thread.currentThread().getName() + ": from singleThreadExecutors"
            );
            log.info(future.get());
        }
        singleThreadExecutor.shutdown();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        Callable<String> task = () -> Thread.currentThread().getName() + ": from FixedThreadPool";
        for (int i = 0; i < 5; i++) {
            Future<String> future = fixedThreadPool.submit(task);
            log.info(future.get());
        }
        fixedThreadPool.shutdown();
    }

    @Slf4j
    private static class SimpleThread extends Thread {
        @Override
        public void run() {
            log.info("{}: extends Thread", Thread.currentThread().getName());
        }
    }
}
