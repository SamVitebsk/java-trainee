package com.andersen.structural.decorator;

import java.io.*;

public class FileDataSource implements DataSource {
    private String fileName;

    public FileDataSource(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeData(String data) {
        File file = new File(fileName);

        try (OutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readData() {
        File file = new File(fileName);
        char[] buffer = null;

        try (FileReader reader = new FileReader(file)) {
            buffer = new char[(int) file.length()];
            reader.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(buffer);
    }
}
