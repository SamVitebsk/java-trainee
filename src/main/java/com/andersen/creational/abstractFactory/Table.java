package com.andersen.creational.abstractFactory;

public abstract class Table {
    private Float length;

    public Table(Float length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Table{" +
                "length=" + length +
                '}';
    }
}
