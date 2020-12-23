package com.andersen.multithreading.commonDataExample;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Data {
    private int num = 0;

    public synchronized void incr() {
        num++;
    }
}
