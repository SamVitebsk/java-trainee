package com.andersen.multithreading.commonDataExample;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MainWithJoin {
    public static void main(String[] args) throws InterruptedException {
        Map<Thread, Data> map = new HashMap<>();
        Data data = new Data();

        for (int i = 0; i < 20; i++) {
            CustomThread thread = new CustomThread(data);
            map.put(thread, data);
        }

        map.forEach((thread, value) -> thread.start());
        for (Thread thread : map.keySet()) {
            thread.join();
        }

        map.forEach(
                (key, value) -> log.info("Thread = {}: value = {}", key, value)
        );
    }
}
