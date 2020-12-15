package com.andersen.structural.facade;

public class VideoFile {
    private String name;
    private String codecName;

    public VideoFile(String name) {
        this.name = name;
        this.codecName = name.substring(name.lastIndexOf('.') + 1);
    }

    public String getName() {
        return name;
    }

    public String getCodecName() {
        return codecName;
    }
}
