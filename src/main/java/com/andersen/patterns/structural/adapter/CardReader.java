package com.andersen.patterns.structural.adapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CardReader implements USB {
    private MemoryCard memoryCard;

    @Override
    public void connectWithUsbCable() {
        memoryCard.insert();
        memoryCard.copyData();
    }
}
