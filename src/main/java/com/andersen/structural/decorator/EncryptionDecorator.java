package com.andersen.structural.decorator;

public class EncryptionDecorator extends DataSourceDecorator {
    public EncryptionDecorator(DataSource wrapper) {
        super(wrapper);
    }

    @Override
    public void writeData(String data) {
        System.out.println("EncryptionDecorator: encode data");
        super.writeData(data);
    }

    @Override
    public String readData() {
        System.out.println("EncryptionDecorator: decode data");
        return super.readData();
    }
}
