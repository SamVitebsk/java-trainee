package com.andersen.structural.decorator;

public interface DataSource {
    void writeData(String data);
    String readData();
}
