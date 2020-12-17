package com.andersen.patterns.structural.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncryptionDecorator extends DataSourceDecorator {
    public EncryptionDecorator(DataSource wrapper) {
        super(wrapper);
    }

    @Override
    public void writeData(String data) {
        log.info("EncryptionDecorator: encode data");
        super.writeData(data);
    }

    @Override
    public String readData() {
        log.info("EncryptionDecorator: decode data");
        return super.readData();
    }
}
