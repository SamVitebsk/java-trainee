package com.andersen.multithreading.commonDataExample;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@ToString
public class CustomThread extends Thread {
    private final Data data;
    private LocalDateTime before;
    private LocalDateTime after;

    public CustomThread(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        before = LocalDateTime.now();
        data.incr();
        after = LocalDateTime.now();
    }
}
