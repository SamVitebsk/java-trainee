package com.andersen.patterns.structural.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        DataSourceDecorator res = new CompressionDecorator(
                                        new EncryptionDecorator(
                                                new FileDataSource("test.txt")
                                        )
        );

        log.info("test string");
        log.info(res.readData());
    }
}
