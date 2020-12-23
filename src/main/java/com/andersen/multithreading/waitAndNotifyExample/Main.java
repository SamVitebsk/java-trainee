package com.andersen.multithreading.waitAndNotifyExample;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        Sender sender = new Sender(data);
        Receiver receiver = new Receiver(data);

        new Thread(receiver).start();
        new Thread(sender).start();
    }
}

