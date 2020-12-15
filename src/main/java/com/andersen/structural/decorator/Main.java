package com.andersen.structural.decorator;

public class Main {
    public static void main(String[] args) {
        DataSourceDecorator res = new CompressionDecorator(
                                        new EncryptionDecorator(
                                                new FileDataSource("test.txt")
                                        )
        );

        res.writeData("test string");
        System.out.println(res.readData());
    }
}
