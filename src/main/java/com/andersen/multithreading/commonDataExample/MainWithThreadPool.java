package com.andersen.multithreading.commonDataExample;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MainWithThreadPool {
    public static void main(String[] args) throws InterruptedException {
        Map<Thread, Data> map = new HashMap<>();
        Data data = new Data();

        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
            CustomThread thread = new CustomThread(data);
            threadPool.submit(thread);
            map.put(thread, data);
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.DAYS);

        map.forEach(
                (key, value) -> log.info(key.toString())
        );
    }
}
