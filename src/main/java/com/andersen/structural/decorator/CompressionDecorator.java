package com.andersen.structural.decorator;

public class CompressionDecorator extends DataSourceDecorator {
    public CompressionDecorator(DataSource wrapper) {
        super(wrapper);
    }

    @Override
    public void writeData(String data) {
        System.out.println("CompressionDecorator: compress data");
        super.writeData(data);
    }

    @Override
    public String readData() {
        System.out.println("CompressionDecorator: decompress data");
        return super.readData();
    }
}
