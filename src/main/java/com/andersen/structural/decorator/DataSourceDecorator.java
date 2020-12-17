package com.andersen.structural.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DataSourceDecorator implements DataSource {
    private DataSource wrapper;

    @Override
    public void writeData(String data) {
        wrapper.writeData(data);
    }

    @Override
    public String readData() {
        return wrapper.readData();
    }
}
