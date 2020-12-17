package com.andersen.structural.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompressionDecorator extends DataSourceDecorator {
    public CompressionDecorator(DataSource wrapper) {
        super(wrapper);
    }

    @Override
    public void writeData(String data) {
        log.info("CompressionDecorator: compress data");
        super.writeData(data);
    }

    @Override
    public String readData() {
        log.info("CompressionDecorator: decompress data");
        return super.readData();
    }
}
