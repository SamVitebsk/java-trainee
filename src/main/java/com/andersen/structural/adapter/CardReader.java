package com.andersen.structural.adapter;

public class CardReader implements USB {
    private MemoryCard memoryCard;

    public CardReader(MemoryCard memoryCard) {
        this.memoryCard = memoryCard;
    }

    @Override
    public void connectWithUsbCable() {
        memoryCard.insert();
        memoryCard.copyData();
    }
}
